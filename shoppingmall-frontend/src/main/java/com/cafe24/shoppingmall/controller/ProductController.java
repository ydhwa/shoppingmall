package com.cafe24.shoppingmall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.shoppingmall.dto.Category;
import com.cafe24.shoppingmall.dto.ProductDetails;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public String productDetails(Model model, @PathVariable("no") Optional<Long> no) {
		if(!no.isPresent()) {
			return "redirect:/main";
		}
		
		ProductDetails product = productService.getProductByNo(no.get());
		model.addAttribute("product", product);
		
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("categories", categoryList);

		return "/product/item";
	}
}