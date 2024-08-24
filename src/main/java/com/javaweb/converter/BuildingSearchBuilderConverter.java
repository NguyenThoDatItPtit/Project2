package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;

import utils.MapUtil;

public class BuildingSearchBuilderConverter {
		public BuildingSearchBuilder toBuildSearchBuilder(Map<String,Object> params, List<String> typeCode) {
			BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																					.setName(MapUtil.getObject(params, "name", String.class))
																					.setFloorarea(MapUtil.getObject(params, "floorarea", Long.class))
																					.setWard(MapUtil.getObject(params, "ward", String.class))
																					.setStreet(MapUtil.getObject(params, "street", String.class))
																					.setDistrictid(MapUtil.getObject(params, "districtid", Long.class))
																					.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Long.class))
																					.setTypeCode(typeCode)
																					.setManagername(MapUtil.getObject(params, "managername", String.class))
																					.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
																					
			return null;
		}
}
