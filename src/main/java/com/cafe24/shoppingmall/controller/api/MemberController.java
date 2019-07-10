package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.vo.MemberVo;

@RestController("memberAPIController")
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	/*
	 * 회원가입 성공 - 회원가입 성공한 멤버(MemberVo) 객체
	 * 회원가입 실패 - 에러 메시지
	 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(@RequestBody MemberVo memberVo) {
		Object result = memberService.join(memberVo);
		
		return JSONResult.success(result);
	}
	
	/*
	 * 중복된 아이디 없음 - 입력했던 아이디
	 * 중복된 아이디 있음 - 에러 메시지
	 */
	@RequestMapping(value="/username", method=RequestMethod.GET)
	public JSONResult checkUsername(@RequestBody String username) {
		Object result = memberService.checkUsername(username);
		
		return JSONResult.success(username);
	}
}
