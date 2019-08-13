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
import com.cafe24.shoppingmall.vo.BucketItemVo;

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
		Long memberNo = null;
		
		// 장바구니 식별자
		if(principal != null) {	// 회원일 때
			memberNo = userService.getUserNo(principal.getName());
			List<BucketItem> bucketList = productService.getMemberBucketList(memberNo);
			model.addAttribute("bucketList", bucketList);
		} else {				// 비회원일 때
			if(identifier == null) {
				identifier = "111111111";
			}
			List<BucketItem> bucketList = productService.getNonMemberBucketList(identifier);
			model.addAttribute("bucketList", bucketList);
		}
		
		Integer totalPrice = productService.getBucketToalPrice(memberNo, identifier);
		model.addAttribute("totalPrice", totalPrice);
		
		return "/bucket/list";
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.PUT)
	public JSONResult2 modifyBucketItemQuantity(@RequestBody BucketItemVo bucketItemVo) {
		if(bucketItemVo.getMemberNo() == null) {
			bucketItemVo.setMemberNo(0L);
		}
		if(bucketItemVo.getIdentifier() == null) {
			bucketItemVo.setIdentifier("111111111");
		}
		
		Boolean result = productService.modifyBucket(bucketItemVo);
		
		return JSONResult2.success(result);
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public JSONResult2 deleteBucketItemList(@RequestBody List<BucketItemVo> bucketList) {
		for(BucketItemVo bucketItemVo: bucketList) {
			if(bucketItemVo.getMemberNo() == null) {
				bucketItemVo.setMemberNo(0L);
			}
			if(bucketItemVo.getIdentifier() == null) {
				bucketItemVo.setIdentifier("111111111");
			}
		}
		
		Boolean result = productService.deleteBucketList(bucketList);
		
		return JSONResult2.success(result);
	}
}