package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) { //로그인 성공시 토큰 만들기. JwTService에 토큰관련 설정. pom.xml에 JWT 의존성 추가
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 토큰 유효성 검사
	 * @param id
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(id);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원검사", notes = "회원 아이디 중복 검사", response = String.class)
	@GetMapping("/idcheck/{id}")
	public ResponseEntity<String> idCheck(
			@PathVariable @ApiParam(value = "인증할 회원의 아이디.", required = true) String id) throws Exception {
		System.out.println(id);
		if(memberService.idCheck(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<String> regist(
			@RequestBody MemberDto memberDto) throws Exception {
		System.out.println(memberDto);
		if(memberService.registerMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<MemberDto> detail(@PathVariable String id) throws Exception {
		return new ResponseEntity<MemberDto>(memberService.getMember(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> update(
			@RequestBody MemberDto memberDto) throws Exception {
		System.out.println(memberDto);
		if(memberService.updateMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws Exception {

		if(memberService.deleteMember(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
