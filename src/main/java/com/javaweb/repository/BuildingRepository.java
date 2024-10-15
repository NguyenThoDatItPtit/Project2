package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;

import main.java.com.javaweb.builder.BuildingSearchBuilder;

public interface BuildingRepository {
	List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode);
}
