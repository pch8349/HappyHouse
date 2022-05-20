package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String id) throws Exception;
	boolean idCheck(String id) throws Exception;
	boolean registerMember(MemberDto memberDto) throws Exception;
	MemberDto getMember(String id) throws Exception;
	boolean updateMember(MemberDto memberDto) throws Exception;
	boolean deleteMember(String id) throws Exception;
}
