package com.cafe24.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.MemberVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinform() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String join(MemberVo memberVo) {
		if(memberVo == null || !userService.join(memberVo)) {
			return "user/join";
		} else {
			return "user/joinsuccess";
		}
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkusername", method=RequestMethod.GET)
	public JSONResult2 checkUsername(@RequestParam(value="username", required=true, defaultValue="") String username) {
		Boolean exist = userService.existUsername(username);
		return JSONResult2.success(exist);
	}
	
}
