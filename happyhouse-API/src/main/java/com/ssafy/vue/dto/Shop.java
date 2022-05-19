package com.ssafy.vue.dto;

public class Shop {
	private int shopNum;
	private String shopName;
	private String shopCat;
	private String sidoName;
	private String gugunName;
	private String dongCode;
	private String dongName;
	private String lat;
	private String lng;
	
	public Shop() {
		super();
	}

	public Shop(int shopNum, String shopName, String shopCat, String sidoName, String gugunName, String dongCode,
			String dongName, String lat, String lng) {
		super();
		this.shopNum = shopNum;
		this.shopName = shopName;
		this.shopCat = shopCat;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.lat = lat;
		this.lng = lng;
	}

	public int getShopNum() {
		return shopNum;
	}

	public void setShopNum(int shopNum) {
		this.shopNum = shopNum;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCat() {
		return shopCat;
	}

	public void setShopCat(String shopCat) {
		this.shopCat = shopCat;
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

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
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
		return "Shop [shopNum=" + shopNum + ", shopName=" + shopName + ", shopCat=" + shopCat + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongCode=" + dongCode + ", dongName=" + dongName + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
}
