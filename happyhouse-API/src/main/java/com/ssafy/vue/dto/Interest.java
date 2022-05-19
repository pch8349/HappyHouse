package com.ssafy.vue.dto;

public class Interest {
	private int idx;
	private String sidoName;
	private String gugunName;
	private String id;

	public Interest() {
		super();
	}

	public Interest(int idx, String sidoName, String gugunName, String id) {
		super();
		this.idx = idx;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.id = id;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "InterestDTO [idx=" + idx + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", id=" + id + "]";
	}
}
