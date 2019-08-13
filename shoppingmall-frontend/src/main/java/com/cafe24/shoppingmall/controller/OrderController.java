package com.cafe24.shoppingmall.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.service.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String orderItems(Model model, @RequestParam("listStr") String listStr) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<BucketItem> orderList = mapper.readValue(listStr, new TypeReference<List<BucketItem>>(){});
		model.addAttribute("orderList", orderList);
		
		Integer totalPrice = productService.getBucketToalPrice(orderList.get(0).getMemberNo(), orderList.get(0).getIdentifier());
		model.addAttribute("totalPrice", totalPrice);
		
		return "order/orderform";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String orderResult() {
		
		return "order/orderresult";
	}
}