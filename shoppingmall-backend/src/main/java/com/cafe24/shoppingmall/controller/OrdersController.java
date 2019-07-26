package com.cafe24.shoppingmall.controller;

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
@RestController("OrdersAPIController")
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	// 주문 등록
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> registOrder(@RequestBody Map<String, Object> ordersMap) {
		if(ordersMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 등록에 실패했습니다."));
		}
		
		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo orders = mapper.convertValue(ordersMap.get("orders"), OrdersVo.class);
		List<BucketItemVo> ordersItemList = mapper.convertValue(ordersMap.get("ordersItemList"), new TypeReference<List<BucketItemVo>>() {});
		
		// 필수 사항들(주문 정보, 주문 품목 정보)에 대하여 체크한다.
		// 필수 사항 외에도 회원 주문인데 회원 번호가 없거나, 비회원인데 비밀번호가 없는 경우도 잡아낸다.
		if(orders == null || ordersItemList == null || ordersItemList.size() == 0 || ("Y".equals(orders.getMemberStatus()) && orders.getMemberNo() == null) || ("N".equals(orders.getMemberStatus()) && orders.getPassword() == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 등록에 실패했습니다."));
		}
		
		Boolean registResult = ordersService.registOrder(orders, ordersItemList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}
	
	// 검색 결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrdersSearchResult(
			@RequestParam(value="field", defaultValue="") String field,
			@RequestParam(value="keyword", defaultValue="") String keyword,
			@RequestParam(value="offset", required=true) Integer offset,
			@RequestParam(value="limit", required=true) Integer limit) {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrderDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 수정
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrder(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteOrder(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}

}
