package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import utils.ConnectionJDBCUtil;
import utils.NumberUtil;
import utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
    public static void joinTable(Map<String,Object> params, List<String> typeCode, StringBuilder sql) {
    	String staffId = (String) params.get("staffId");
    	if(StringUtil.checkString(staffId)) {
    		sql.append("inner join assignmentbuilding on b.id = assignmentbuilding.buildingid");
    	}
    	if(typeCode != null && typeCode.size() != 0) {
    		sql.append("inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
    		sql.append("inner join renttype on renttype.id = buildingrenttype.renttypeid ");
    	}
    }
    
    public static void queryNormal(Map<String,Object> params,StringBuilder where) {
    	for(Map.Entry<String, Object> it : params.entrySet()) {
    		if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
    			String value = it.getValue().toString();
    			if(StringUtil.checkString(value)) {
    				if(NumberUtil.isNumber(value) == true) {
    					where.append(" AND b." + it.getKey() + " = " + value);
    				}
    				else {
    					where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
    				}
    			}
    		}
    	}
    }
    
    public static void querySpecial(Map<String,Object> params,List<String> typeCode, StringBuilder where) {
    	String staffId = (String) params.get("staffId");
    	if(StringUtil.checkString(staffId)) {
    		where.append(" AND assignmentbuilding.staffid = " + staffId);
    	}
    	String rentAreaFrom = (String) params.get("areaFrom");
    	String rentAreaTo = (String) params.get("areaTo");
    	if(StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
    		where.append(" AND EXISTS (SELECT * from rentarea r WHERE b.id = r.buildingid ");
    		if(StringUtil.checkString(rentAreaFrom)) {
    			where.append(" AND r.value >= " + rentAreaFrom);
    		}
    		if(StringUtil.checkString(rentAreaTo)) {
    			where.append(" AND r.value <= " + rentAreaTo);
    		}
    		where.append(" ) "); 
    	}
    	
    	String rentPriceFrom = (String) params.get("areaPriceFrom");
    	String rentPriceTo = (String) params.get("areaPriceTo");
    	if(StringUtil.checkString(rentPriceFrom) || StringUtil.checkString(rentPriceTo)) {
    		if(StringUtil.checkString(rentPriceFrom)) {
    			where.append(" AND b.rentprice >= " + rentPriceFrom);
    		}
    		if(StringUtil.checkString(rentPriceTo)) {
    			where.append(" AND b.rentprice <= " + rentPriceTo);
    		}
    	}
    	//java 7
//    	if(typeCode != null && typeCode.size() != 0) {
//    		List<String> code = new ArrayList<>();
//    		for(String item : typeCode) {
//    			code.add("'" + item + "'");
//    		}
//    		where.append(" AND renttype.code IN(" + String.join(",", code) + ") ");
//    	}
    	//java 8
    	if(typeCode != null && typeCode.size() != 0) {
    		where.append(" AND(");
    		String sql = typeCode.stream().map(it -> "renttype.code like " + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
    		where.append(sql);
    		where.append(" ) ");
    	}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("select b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.brokeragefee, b.rentprice, b.servicefee from building b ");
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append("GROUP BY b.id;");
		sql.append(where);
    	List<BuildingEntity> result = new ArrayList<>();
    	try(Connection conn = ConnectionJDBCUtil.getConnection();
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql.toString());){
    		while(rs.next()) {
    			BuildingEntity building = new BuildingEntity();
    			building.setId(rs.getLong("id"));
    			building.setName(rs.getString("name"));
    			building.setStreet(rs.getString("street"));
    			building.setWard(rs.getString("ward"));
    			building.setFloorarea(rs.getLong("floorarea"));
    			building.setDistrictid(rs.getLong("districtid"));
    			building.setBrokeragefee(rs.getLong("brokeragefee"));
    			building.setManagername(rs.getString("managername"));
    			building.setManagerphonenumber(rs.getString("managerphonenumber"));
    			building.setRentprice(rs.getLong("rentprice"));
    			building.setServicefee(rs.getString("servicefee"));
    			result.add(building);
    		}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("connected database failed.....");
    	}
    	return result;
	}
	
}
