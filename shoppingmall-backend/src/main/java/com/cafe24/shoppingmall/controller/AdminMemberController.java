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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminMemberAPIController")
@RequestMapping("/api/admin/members")
@Api(value="/api/admin/members", description="관리자 회원 컨트롤러", consumes="application/json")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value="회원 상세조회", response=MemberVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 회원 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원정보 열람에 실패했습니다."));
		}
		
		MemberVo memberVo = memberService.showMemberDetailsToAdmin(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	
	// swagger로는 테스트 할 수 없다.
	@ApiOperation(value="회원 검색목록 조회", response=MemberVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="paramMap", value="검색조건", dataType="string", paramType="query")
	})
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

	@ApiOperation(value="회원 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberVo", value="수정할 회원 정보", dataType="MemberVo", paramType="body")
	})
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
