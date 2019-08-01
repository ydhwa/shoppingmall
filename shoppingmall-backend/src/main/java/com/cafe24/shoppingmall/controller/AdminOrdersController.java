package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
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
import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.service.OrdersService;
import com.cafe24.shoppingmall.vo.OrdersVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminOrdersAPIController")
@RequestMapping("/api/admin/orders")
@Api(value="/api/admin/orders", description="관리자 주문 컨트롤러", consumes="application/json")
public class AdminOrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	// swagger로는 테스트 할 수 없다.
	@ApiOperation(value="주문 검색목록 조회", response=OrdersSummaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="paramMap", value="검색조건", dataType="string", paramType="query")
	})
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrdersSearchResultToAdmin(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문내역 검색에 실패했습니다."));
		}
		
		List<OrdersSummaryDto> ordersList = ordersService.showOrdersBySearchToAdmin(paramMap);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersList));
	}
	
	@ApiOperation(value="주문 상세조회", response=OrdersDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 주문 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrderDetailsToAdmin(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문내역 검색에 실패했습니다."));
		}
		
		OrdersDetailsDto orderResult = ordersService.showOrdersDetailsToAdmin(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderResult));	}
	
	// 주문 상태 수정만 가능
	@ApiOperation(value="주문 상태 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name="ordersVo", value="수정할 주문 정보", dataType="OrdersVo", paramType="body")
	})
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrderStatusToAdmin(@RequestBody OrdersVo ordersVo) {
		// path variable check
		if(ordersVo == null || ordersVo.getNo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문상태 수정에 실패했습니다."));
		}
		
		Boolean modifyStatus = ordersService.modifyOrderStatusToAdmin(ordersVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyStatus));
	}

}
