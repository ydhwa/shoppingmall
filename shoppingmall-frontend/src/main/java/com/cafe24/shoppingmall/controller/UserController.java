package com.cafe24.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping( "/user"  )
public class UserController {
	
	@RequestMapping(method={RequestMethod.GET, RequestMethod.POST}, value="/login" )
	public String login() {
		return "user/login";
	}
}
