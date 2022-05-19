package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Notice;
import com.ssafy.vue.mapper.NoticeMapper;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<Notice> selectAll() throws Exception {
		return noticeMapper.selectAll();
	}

	@Override
	public Notice selectOne(int idx) throws Exception {
		return noticeMapper.selectOne(idx);
	}

	@Override
	public boolean insert(Notice notice) throws Exception {
		return noticeMapper.insert(notice) == 1;
	}

	@Override
	public boolean update(Notice notice) throws Exception {
		return noticeMapper.update(notice) == 1;
	}

	@Override
	public boolean delete(int idx) throws Exception {
		return noticeMapper.delete(idx) == 1;
	}

}
