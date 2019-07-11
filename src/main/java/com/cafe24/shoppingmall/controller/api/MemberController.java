package com.cafe24.shoppingmall.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONResult join(@Valid @RequestBody MemberVo memberVo, BindingResult bindingResult) throws BindException {
		// Validation check
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
		Object result = memberService.join(memberVo);
		
		return JSONResult.success(result);
	}
	
	@RequestMapping(value="/username", method=RequestMethod.GET)
	public JSONResult checkUsername(@Valid @RequestBody String username) {
		Object result = memberService.checkUsername(username);
		
		return JSONResult.success(result);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login(@RequestBody MemberVo memberVo) {
		Object result = memberService.login(memberVo);
		
		return JSONResult.success(result);
	}
}
