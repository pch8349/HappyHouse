package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.Hospital;
import com.ssafy.vue.dto.Interest;
import com.ssafy.vue.dto.Shop;

public interface InterestMapper {
	public abstract List<Interest> selectAllById(String id) throws SQLException;
	public abstract Interest selectOne(int idx) throws SQLException;
	public abstract int insert(Interest interest) throws SQLException;
	public abstract int update(Interest interest) throws SQLException;
	public abstract int delete(int idx) throws SQLException;
	public abstract List<Hospital> selectHospital(@Param("sidoName") String sidoName, @Param("gugunName") String gugunName) throws SQLException;
	public abstract List<Shop> selectShop(@Param("sidoName") String sidoName, @Param("gugunName") String gugunName) throws SQLException;
}
