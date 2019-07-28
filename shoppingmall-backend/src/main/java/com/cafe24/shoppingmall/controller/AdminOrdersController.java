package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.OrdersService;
import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.OrdersVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 주문에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("adminOrdersAPIController")
@RequestMapping("/admin/orders")
public class AdminOrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	// 검색 결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrdersSearchResultToAdmin(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit") || (!paramMap.containsKey("memberNo") || (!paramMap.containsKey("identifier")))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문내역 검색에 실패했습니다."));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrderDetailsToAdmin(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 수정(주문 상태 수정만 가능)
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrderStatusToAdmin(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}

}
