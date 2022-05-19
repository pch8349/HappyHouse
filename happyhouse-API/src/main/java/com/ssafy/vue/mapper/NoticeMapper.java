package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.Notice;


public interface NoticeMapper {
	public abstract List<Notice> selectAll() throws SQLException;
	public abstract Notice selectOne(int idx) throws SQLException;
	public abstract int insert(Notice notice) throws SQLException;
	public abstract int update(Notice notice) throws SQLException;
	public abstract int delete(int idx) throws SQLException;
}
