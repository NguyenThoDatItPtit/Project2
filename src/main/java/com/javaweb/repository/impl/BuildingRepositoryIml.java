package com.javaweb.repository.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import utils.NumberUtil;
import utils.StringUtil;

@Repository
@Primary
public class BuildingRepositoryIml implements BuildingRepository{
	
	public static void queryNormal(Map<String,Object> params,StringBuilder sql) {
		
		String sql_findName = (String) params.get("name");
		String sql_findDistrict = (String) params.get("districtId");
		String sql_findWard = (String) params.get("ward");
		String sql_findStreet = (String) params.get("street");
		String sql_findFloorArea = (String) params.get("floorArea");
		String sql_findNumberofbasement = (String) params.get("numberofbasement");
		String sql_findNameManager
		
		//tìm theo tên
		if(StringUtil.checkString(sql_findName)) {
			sql.append("AND b.name like '%" + sql_findName + "%' ");
		}
		
		// tìm theo quận
		if(NumberUtil.isNumber(sql_findDistrict)) {
			sql.append("AND b.districtid = " + sql_findDistrict + " ");
		}
		
		// tìm theo phường
		if(StringUtil.checkString(sql_findWard)) {
			sql.append("AND b.ward like '%" + sql_findWard + "%' ");
		}
		
		// tìm theo đường
		if(StringUtil.checkString(sql_findStreet)) {
			sql.append("AND b.street like '%" + sql_findStreet + "%' ");
		}
		
		// tìm theo diện tích sàn
		if(NumberUtil.isNumber(sql_findFloorArea)) {
			sql.append("AND b.floorarea = " + sql_findFloorArea + " ");
		}
		
		// tìm theo số tầng hầm
		if(NumberUtil.isNumber(sql_findNumberofbasement)) {
			sql.append("AND b.numberofbasement = " + sql_findNumberofbasement + " ");
		}
		
		// 
		if(StringUtil.checkString(sql_findStreet)) {
			sql.append("AND b.street like '%" + sql_findStreet + "%' ");
		}
		
		
	}
	

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		//sql native
		StringBuilder sql = new StringBuilder("select *from building b where 1 = 1 ");
		queryNormal(params, sql);
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
	
}
