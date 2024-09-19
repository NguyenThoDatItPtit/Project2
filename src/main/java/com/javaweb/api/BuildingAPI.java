package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;

	@GetMapping(value = "/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name="typeCode",required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params,typeCode);
		return result;
	}
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	@PostMapping(value = "/api/building")
//	@Transactional
//	public void createBuiding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
//		BuildingEntity buildingEntity = new BuildingEntity();
//		buildingEntity.setName(buildingRequestDTO.getName());
//		buildingEntity.setStreet(buildingRequestDTO.getStreet());
//		buildingEntity.setWard(buildingRequestDTO.getWard());
//		DistrictEntity districtEntity = new DistrictEntity();
//		districtEntity.setId(buildingRequestDTO.getDistrictId());
//		buildingEntity.setDistrict(districtEntity);
//		entityManager.persist(buildingEntity);
//		System.out.println("ok");
//	}
	
//	@PutMapping(value = "/api/building")
//	@Transactional
//	public void updateBuiding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
//		BuildingEntity buildingEntity = new BuildingEntity();
//		buildingEntity.setId(1L);
//		buildingEntity.setName(buildingRequestDTO.getName());
//		buildingEntity.setStreet(buildingRequestDTO.getStreet());
//		buildingEntity.setWard(buildingRequestDTO.getWard());
//		DistrictEntity districtEntity = new DistrictEntity();
//		districtEntity.setId(buildingRequestDTO.getDistrictId());
//		buildingEntity.setDistrict(districtEntity);
//		entityManager.merge(buildingEntity);
//		System.out.println("ok");
//	}
//	
//	@DeleteMapping(value = "/api/building/{id}")
//	@Transactional
//	public void deleteBuilding(@PathVariable Long id) {
//		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
//		entityManager.remove(buildingEntity);
//	}
}































