package com.cafe24.shoppingmall.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.dto.Category;
import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.dto.ProductDetails;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public String productDetails(Principal principal, Model model, @PathVariable("no") Optional<Long> no) {
		if(!no.isPresent()) {
			return "redirect:/list";
		}
		
		ProductDetails product = productService.getProductByNo(no.get());
		model.addAttribute("product", product);
		
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("categories", categoryList);
		
		if(principal != null) {
			Long memberNo = 1L;
			model.addAttribute("memberNo", principal.getName());
			System.out.println(principal.getName());
		}

		return "/product/item";
	}
	
	@RequestMapping(value="/optionitem", method=RequestMethod.GET)
	@ResponseBody
	public JSONResult2 productItem(@RequestParam HashMap<String, String> paramMap) {
		if(!paramMap.containsKey("productNo") || !paramMap.containsKey("optionValueKeys")) {
			return JSONResult2.fail("상품을 찾을 수 없습니다.");
		}
		
		ProductOptionItemVo productOptionItem = productService.getProductItem(paramMap);
		return JSONResult2.success(productOptionItem);
	}
}