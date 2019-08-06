package com.cafe24.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.shoppingmall.service.UserService;

@Controller
@RequestMapping( "/user"  )
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinform() {
		return "/user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join() {
		if(userService.join()) {
			return "user/joinsuccess";
		} else {
			return "user/join";
		}
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
}
