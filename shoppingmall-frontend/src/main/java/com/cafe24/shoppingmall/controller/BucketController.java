package com.cafe24.shoppingmall.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.service.UserService;

@Controller
@RequestMapping("/bucket")
public class BucketController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONResult2 registBucket(@RequestBody Map<String, Object> bucketList) {
		Boolean result = productService.registBucket(bucketList);
		
		return JSONResult2.success(result);
	}
	
	@RequestMapping(value={"", "/list"}, method=RequestMethod.GET)
	public String showBucketItemList(Principal principal, 
			@CookieValue(value="identifier", required=false) String identifier,
			Model model) {
		// 장바구니 식별자
		if(principal != null) {	// 회원일 때
			Long memberNo = userService.getUserNo(principal.getName());
			List<BucketItem> bucketList = productService.getMemberBucketList(memberNo);
			model.addAttribute("bucketList", bucketList);
		} else {				// 비회원일 때
			if(identifier == null) {
				identifier = "111111111";
			}
			List<BucketItem> bucketList = productService.getNonMemberBucketList(identifier);
			model.addAttribute("bucketList", bucketList);
		}
		
		return "/bucket/list";
	}
}