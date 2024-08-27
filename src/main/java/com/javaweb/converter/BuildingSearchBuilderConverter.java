package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;

import utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
		public BuildingSearchBuilder toBuildSearchBuilder(Map<String,Object> params, List<String> typeCode) {
			BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																					.setName(MapUtil.getObject(params, "name", String.class))
																					.setFloorarea(MapUtil.getObject(params, "floorarea", Long.class))
																					.setWard(MapUtil.getObject(params, "ward", String.class))
																					.setStreet(MapUtil.getObject(params, "street", String.class))
																					.setDistrictid(MapUtil.getObject(params, "districtId", Long.class))
																					.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Long.class))
																					.setTypeCode(typeCode)
																					.setManagername(MapUtil.getObject(params, "managerName", String.class))
																					.setManagerphonenumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
																					.setRentPriceFrom(MapUtil.getObject(params, "rentPriceRrom", Long.class))
																					.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
																					.setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
																					.setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
																					.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
																					.build();
			return buildingSearchBuilder;
		}
}
