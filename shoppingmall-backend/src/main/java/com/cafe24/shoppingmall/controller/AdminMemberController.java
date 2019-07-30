package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.vo.MemberVo;

/**
 * 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("adminMemberAPIController")
@RequestMapping("/api/admin/members")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원정보 열람에 실패했습니다."));
		}
		
		MemberVo memberVo = memberService.showMemberDetailsToAdmin(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	
	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMembers(@RequestParam HashMap<String, String> paramMap) {
		if(!paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원정보 검색에 실패했습니다."));
		}
		// 원활한 검색을 위한 없는 값 집어넣기 (날짜 데이터 제외)
		if(!paramMap.containsKey("username")) {
			paramMap.put("username", "");
		}
		if(!paramMap.containsKey("name")) {
			paramMap.put("name", "");
		}
		if(!paramMap.containsKey("phoneNumber")) {
			paramMap.put("phoneNumber", "");
		}
		if(!paramMap.containsKey("email")) {
			paramMap.put("email", "");
		}
		
		List<MemberVo> memberList = memberService.searchMembersToAdmin(paramMap);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberList));
	}

	// 수정
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyMemberToAdmin(@RequestBody MemberVo memberVo) {
		// 별도의 validation check를 걸지는 않음. 필요하다면 추후에 수정하지 않을까?
		if(memberVo == null || memberVo.getNo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원정보 수정에 실패했습니다."));
		}
		
		Boolean modifyResult = memberService.modifyToAdmin(memberVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyResult));
	}
}
