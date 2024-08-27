package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import utils.ConnectionJDBCUtil;
import utils.NumberUtil;
import utils.StringUtil;;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
    	Long staffId = buildingSearchBuilder.getStaffId();
    	if(staffId != null) {
    		sql.append("inner join assignmentbuilding on b.id = assignmentbuilding.buildingid");
    	}
    	List<String> typeCode = buildingSearchBuilder.getTypeCode();
    	if(typeCode != null && typeCode.size() != 0) {
    		sql.append("inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
    		sql.append("inner join renttype on renttype.id = buildingrenttype.renttypeid ");
    	}
    }
    
    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {	
//    	for(Map.Entry<String, Object> it : params.entrySet()) {
//    		if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
//    			String value = it.getValue().toString();
//    			if(StringUtil.checkString(value)) {
//    				if(NumberUtil.isNumber(value) == true) {
//    					where.append(" AND b." + it.getKey() + " = " + value);
//    				}
//    				else {
//    					where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
//    				}
//    			}
//    		}
//    	}
    	try {
    		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
    		for(Field item : fields) {
    			//su dung true de co quyen truy cap vao cac field
    			item.setAccessible(true);
    			//su dung giong map dung getName de lay ten key
    			String fieldName = item.getName();
    			if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
    				//  get doi tuong de lay value
    				Object	 value = item.get(buildingSearchBuilder);
        			if(value != null) {
        				if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
        					where.append(" AND b." + fieldName + " = " + value);
        				}
        				else {
        					where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
        				}
        			}
    			}
    		}
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
    }
    
    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
    	Long staffId = buildingSearchBuilder.getStaffId();
    	if(staffId != null) {
    		where.append(" AND assignmentbuilding.staffid = " + staffId);
    	}
    	Long rentAreaFrom = buildingSearchBuilder.getRentPriceFrom();
    	Long rentAreaTo = buildingSearchBuilder.getAreaTo();
    	if(rentAreaFrom != null || rentAreaTo != null) {
    		where.append(" AND EXISTS (SELECT * from rentarea r WHERE b.id = r.buildingid ");
    		if(rentAreaFrom != null) {
    			where.append(" AND r.value >= " + rentAreaFrom);
    		}
    		if(rentAreaTo != null) {
    			where.append(" AND r.value <= " + rentAreaTo);
    		}
    		where.append(" ) "); 
    	}
    	
    	Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
    	Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
    	if(rentPriceFrom != null || rentPriceTo != null) {
    		if(rentPriceFrom != null) {
    			where.append(" AND b.rentprice >= " + rentPriceFrom);
    		}
    		if(rentPriceTo != null) {
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
    	List<String> typeCode = buildingSearchBuilder.getTypeCode();
    	if(typeCode != null && typeCode.size() != 0) {
    		where.append(" AND(");
    		String sql = typeCode.stream().map(it -> "renttype.code like " + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
    		where.append(sql);
    		where.append(" ) ");
    	}
	}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("select b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.brokeragefee, b.rentprice, b.servicefee from building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
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
