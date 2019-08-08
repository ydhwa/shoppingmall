package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.dto.Category;
import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.dto.ProductDetails;
import com.cafe24.shoppingmall.dto.ProductSummary;
import com.cafe24.shoppingmall.dto.User;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	private static final int PRODUCT_PER_PAGE = 1000000;

	@RequestMapping(value={"", "/main"}, method=RequestMethod.GET)
	public String adminMain() {
		return "admin/main/index";
	}
	
	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String adminUserList(Model model, @RequestParam HashMap<String, String> paramMap) {
		if(!paramMap.containsKey("offset")) {
			paramMap.put("offset", "0");
		} else if(!isInteger(paramMap.get("offset"))) {
			paramMap.replace("offset", "0");
		}
		int offset = Integer.parseInt(paramMap.get("offset"));
		
		List<User> userList = userService.getUserList(offset, PRODUCT_PER_PAGE, paramMap);
		
		model.addAttribute("users", userList);
		
		return "admin/user/list";
	}
	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@RequestMapping(value="/product/list", method=RequestMethod.GET)
	public String adminProductList(Model model, @RequestParam HashMap<String, String> paramMap) {
		if(!paramMap.containsKey("offset")) {
			paramMap.put("offset", "0");
		} else if(!isInteger(paramMap.get("offset"))) {
			paramMap.replace("offset", "0");
		}
		int offset = Integer.parseInt(paramMap.get("offset"));
		
		List<ProductSummary> productList = productService.getProductListAsAdmin(offset, PRODUCT_PER_PAGE, paramMap);
		
		model.addAttribute("products", productList);
		
		return "admin/product/list";
	}
	
	@RequestMapping(value="/product/regist", method=RequestMethod.GET)
	public String adminProductRegistForm(Model model) {
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("categories", categoryList);
		
		return "admin/product/regist";
	}
	
	@ResponseBody
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	public JSONResult2 adminProductRegist(@RequestBody Map<String, Object> paramMap) {
		// 상품 등록에 성공했습니다.
		// 버튼) 상품 더 등록하기 / 상품 목록 보기
		Boolean result = productService.registProduct(paramMap);
		
		return JSONResult2.success(result);
	}
	
	@RequestMapping(value="/product/regist-success", method=RequestMethod.GET)
	public String adminProductRegistSuccess() {
		return "admin/product/regist-success";
	}
	
	@RequestMapping(value="/product/{no}", method=RequestMethod.GET)
	public String productDetails(Model model, @PathVariable("no") Optional<Long> no) {
		if(!no.isPresent()) {
			return "redirect:/admin";
		}
		ProductDetails product = productService.getProductByNoAsAdmin(no.get());
		model.addAttribute("product", product);

		return "admin/product/item";
	}
}