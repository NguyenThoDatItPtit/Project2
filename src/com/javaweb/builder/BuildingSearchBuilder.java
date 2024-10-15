package com.javaweb.builder;

import java.util.List;
import java.util.ArrayList;

public class BuildingSearchBuilder {
	private String name;
	private String street;
	private String ward;
	private Long  districtid;
	private Long floorarea;
	private Long rentPriceFrom;
	private Long rentPriceTo;
	private String managername;
	private String managerphonenumber;
	private Long numberofbasement;
	private Long areaFrom;
	private Long areaTo;
	private Long staffId;
	private List<String> typeCode = new ArrayList<>();
	
	
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = name;
		this.street = street;
		this.ward = ward;
		this.districtid = districtid;
		this.floorarea = floorarea;
		this.rentPriceFrom = rentPriceFrom;
		this.rentPriceTo = rentPriceTo;
		this.managername = managername;
		this.managerphonenumber = managerphonenumber;
		this.numberofbasement = numberofbasement;
		this.areaFrom = areaFrom;
		this.areaTo = areaTo;
		this.staffId = staffId;
		this.typeCode = typeCode;
	}
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public String getWard() {
		return ward;
	}
	public Long getDistrictid() {
		return districtid;
	}
	public Long getFloorarea() {
		return floorarea;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Long getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagername() {
		return managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public Long getNumberofbasement() {
		return numberofbasement;
	}
	public Long getAreaFrom() {
		return areaFrom;
	}
	public Long getAreaTo() {
		return areaTo;
	}
	public Long getStaffId() {
		return staffId;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	
	public static class Builder{
		private String name;
		private String street;
		private String ward;
		private Long  districtid;
		private Long floorarea;
		private Long rentPriceFrom;
		private Long rentPriceTo;
		private String managername;
		private String managerphonenumber;
		private Long numberofbasement;
		private Long areaFrom;
		private Long areaTo;
		private Long staffId;
		private List<String> typeCode = new ArrayList<>();
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setDistrictid(Long districtid) {
			this.districtid = districtid;
			return this;
		}
		public Builder setFloorarea(Long floorarea) {
			this.floorarea = floorarea;
			return this;
		}
		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setManagername(String managername) {
			this.managername = managername;
			return this;
		}
		public Builder setManagerphonenumber(String managerphonenumber) {
			this.managerphonenumber = managerphonenumber;
			return this;
		}
		public Builder setNumberofbasement(Long numberofbasement) {
			this.numberofbasement = numberofbasement;
			return this;
		}
		public Builder setAreaFrom(Long areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}
		public Builder setAreaTo(Long areaTo) {
			this.areaTo = areaTo;
			return this;
		}
		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
}
