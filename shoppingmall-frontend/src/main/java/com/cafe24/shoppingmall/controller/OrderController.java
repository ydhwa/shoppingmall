package com.cafe24.shoppingmall.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.dto.JSONResult2;
import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.service.BucketService;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.OrdersVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final static int PRODUCT_PER_PAGE = 10000000;
	
	@Autowired
	private UserService userService;
	@Autowired
	private BucketService bucketService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String orderItems(Model model, @RequestParam("listStr") String listStr) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<BucketItem> orderList = mapper.readValue(listStr, new TypeReference<List<BucketItem>>(){});
		model.addAttribute("orderList", orderList);
		
		Integer totalPrice = bucketService.getBucketTotalPrice(orderList.get(0).getMemberNo(), orderList.get(0).getIdentifier());
		model.addAttribute("totalPrice", totalPrice);
		
		return "order/orderform";
	}
	
	@ResponseBody
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public JSONResult2 registOrder(@RequestBody Map<String, Object> paramMap) {
		String result = orderService.registOrder(paramMap);
		
		if(result != null) {
			return JSONResult2.success(result);
		} else {
			return JSONResult2.fail("주문 실패");
		}
	}
	
	@RequestMapping(value="/non-member", method=RequestMethod.GET)
	public String orderResult() {
		
		return "order/certification";
	}
	@RequestMapping(value="/non-member", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String orderCertificate(Model model, OrdersVo ordersVo) {
		OrdersDetailsDto order = orderService.getOrderAsNonMember(ordersVo);
		model.addAttribute("order", order);
		
		return "order/item";
	}
	
	@RequestMapping(value="/member", method=RequestMethod.GET)
	public String orderList(Principal principal, Model model, @RequestParam Map<String, String> paramMap) {
		if(!paramMap.containsKey("offset")) {
			paramMap.put("offset", "0");
		} else if(!isInteger(paramMap.get("offset"))) {
			paramMap.replace("offset", "0");
		}
		int offset = Integer.parseInt(paramMap.get("offset"));
		
		List<OrdersSummaryDto> orderList = orderService.getOrderList(userService.getUserNo(principal.getName()), offset, PRODUCT_PER_PAGE);
		
		model.addAttribute("orderSummaryList", orderList);
		
		return "order/list";
	}
	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@RequestMapping(value="/member/view/{no}", method=RequestMethod.GET)
	public String orderDetails(Principal principal, Model model, @PathVariable("no") Optional<Long> no) {
		if(!no.isPresent()) {
			return "redirect:/";
		}

		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setMemberNo(userService.getUserNo(principal.getName()));
		OrdersDetailsDto order = orderService.getOrderAsMember(no.get(), ordersVo);
		
		model.addAttribute("order", order);
		
		return "order/item";
	}
}