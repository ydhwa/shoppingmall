package com.cafe24.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value={"", "/main"}, method=RequestMethod.GET)
	public String adminMain() {
		return "admin/main/index";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String adminUserList() {
		return "admin/user/list";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String adminProductList() {
		return "admin/product/list";
	}
	
	@RequestMapping(value="/product/regist", method=RequestMethod.GET)
	public String adminProductRegist() {
		return "admin/product/regist";
	}
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	public String adminProductResist() {
		// 상품 등록에 성공했습니다.
		// 버튼) 상품 더 등록하기 / 상품 목록 보기
		return "admin/product/regist-success";
	}
}