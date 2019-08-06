package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.shoppingmall.dto.Category;
import com.cafe24.shoppingmall.dto.ProductSummary;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;

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
}