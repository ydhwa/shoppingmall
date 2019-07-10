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

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(@RequestBody MemberVo memberVo) {
		// 성공 - 회원가입 성공한 멤버 객체
		// 실패 - 에러 메시지
		Object result = memberService.join(memberVo);
		
		return JSONResult.success(result);
	}
	
	@RequestMapping(value="/username", method=RequestMethod.GET)
	public JSONResult checkUsername(@RequestBody String username) {
		// 성공 - 입력했던 아이디
		// 실패 - 에러 메시지
		Object result = memberService.checkUsername(username);
		
		return JSONResult.success(username);
	}
}
