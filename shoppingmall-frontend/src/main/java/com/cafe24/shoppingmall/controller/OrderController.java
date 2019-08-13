package com.cafe24.shoppingmall.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.dto.JSONResult2;
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
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONResult2 registOrder(@RequestBody Map<String, Object> paramMap) {
		Boolean result = productService.registOrder(paramMap);
		
		return JSONResult2.success(result);
	}
	
	@RequestMapping(value="/result", method=RequestMethod.GET)
	public String orderResult() {
		
		return "order/orderresult";
	}
}