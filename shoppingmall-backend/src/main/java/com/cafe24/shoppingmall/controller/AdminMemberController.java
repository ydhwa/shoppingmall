package com.cafe24.shoppingmall.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;

/**
 * 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("adminMemberAPIController")
@RequestMapping("/admin/members")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;

	// 수정
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyMemberData(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMembers(
			@RequestParam(value="field", defaultValue="") String field,
			@RequestParam(value="keyword", defaultValue="") String keyword,
			@RequestParam(value="offset", required=true) Integer offset,
			@RequestParam(value="limit", required=true) Integer limit) {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
}
