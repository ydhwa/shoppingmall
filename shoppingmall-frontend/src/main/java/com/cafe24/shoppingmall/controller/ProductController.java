package com.cafe24.shoppingmall.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public String productDetails(Principal principal,
			@CookieValue(value="identifier", required = false) String identifier,
			HttpServletResponse response,
			Model model, @PathVariable("no") Optional<Long> no) {
		if(!no.isPresent()) {
			return "redirect:/list";
		}
		
		ProductDetails product = productService.getProductByNo(no.get());
		model.addAttribute("product", product);
		
		List<Category> categoryList = categoryService.getAllCategories();
		model.addAttribute("categories", categoryList);
		
		// 장바구니 식별자
		if(principal != null) {
			Long memberNo = userService.getUserNo(principal.getName());
			model.addAttribute("memberNo", memberNo);
		} else {
			String identifierStr = makeIdentifier();
			if(identifier == null) {
				Cookie cookie = new Cookie("identifier", identifierStr);
				cookie.setMaxAge(14 * 24 * 60 * 60);	// 유효기간 14일
				identifier = cookie.getValue();
				response.addCookie(cookie);
			}
			model.addAttribute("identifier", identifier);
		}

		return "/product/item";
	}
	// 장바구니 식별자 생성(비회원용)
	private String makeIdentifier() {
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
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