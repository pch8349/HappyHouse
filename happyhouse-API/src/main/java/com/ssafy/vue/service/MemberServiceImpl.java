package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getId() == null || memberDto.getPassword() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean idCheck(String id) throws Exception {
		return memberMapper.idCheck(id) == 1; // true : 1, false : 0
	}

	@Override
	public boolean registerMember(MemberDto memberDto) throws Exception {
		return memberMapper.registerMember(memberDto) == 1;
	}

	@Override
	public MemberDto getMember(String id) throws Exception {
		return memberMapper.getMember(id);
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws Exception {
		return memberMapper.updateMember(memberDto) == 1;
	}

	@Override
	public boolean deleteMember(String id) throws Exception {
		return memberMapper.deleteMember(id) == 1;
	}

}
