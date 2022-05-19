package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Notice;


public interface NoticeService {
	public abstract List<Notice> selectAll() throws Exception;
	public abstract Notice selectOne(int idx) throws Exception;
	public abstract boolean insert(Notice notice) throws Exception;
	public abstract boolean update(Notice notice) throws Exception;
	public abstract boolean delete(int idx) throws Exception;
}
