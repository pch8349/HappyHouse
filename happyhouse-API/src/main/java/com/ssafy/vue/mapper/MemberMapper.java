package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.MemberDto;


public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String id) throws SQLException;
	int idCheck(String id) throws Exception;
	int registerMember(MemberDto memberDto) throws Exception;
	MemberDto getMember(String id) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	int deleteMember(String id) throws Exception;
}