package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Hospital;
import com.ssafy.vue.dto.Interest;
import com.ssafy.vue.dto.Shop;

public interface InterestService {
	public abstract List<Interest> selectAllById(String id) throws Exception;
	public abstract Interest selectOne(int idx) throws Exception;
	public abstract boolean insert(Interest interest) throws Exception;
	public abstract boolean update(Interest interest) throws Exception;
	public abstract boolean delete(int idx) throws Exception;
	public abstract List<Hospital> selectHospital(String sidoName, String gugunName) throws Exception;
	public abstract List<Shop> selectShop(String sidoName, String gugunName) throws Exception;
	public abstract String hospitalRoute(String sidoName, String gugunName) throws Exception;
	public abstract String shopRoute(String sidoName, String gugunName) throws Exception;
	public abstract String showHospitalShop(String sidoName, String gugunName) throws Exception;
}
