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
public class MainController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	private static final int PRODUCT_PER_PAGE = 6;
	
	@RequestMapping(value={"/", "/main"}, method=RequestMethod.GET)
	public String main(Model model, @RequestParam HashMap<String, String> paramMap) {
		
		List<ProductSummary> productList = productService.getAllProducts(0, PRODUCT_PER_PAGE, paramMap);
		List<Category> categoryList = categoryService.getAllCategories();
		
		model.addAttribute("products", productList);
		model.addAttribute("categories", categoryList);
		
		return "main/index";
	}
}