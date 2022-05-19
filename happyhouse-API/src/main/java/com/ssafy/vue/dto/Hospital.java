package com.ssafy.vue.dto;

public class Hospital {
	private int no;
	private String hospitalName;
	private String sidoName;
	private String gugunName;
	private String lat;
	private String lng;
	
	public Hospital() {
		super();
	}

	public Hospital(int no, String hospitalName, String sidoName, String gugunName, String lat, String lng) {
		super();
		this.no = no;
		this.hospitalName = hospitalName;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.lat = lat;
		this.lng = lng;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Hospital [no=" + no + ", hospitalName=" + hospitalName + ", sidoName=" + sidoName + ", gugunName="
				+ gugunName + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
