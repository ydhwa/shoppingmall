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
import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.service.OrdersService;
import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.OrdersVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("ordersAPIController")
@RequestMapping("/api/orders")
@Api(value="/api/orders", description="주문 컨트롤러", consumes="application/json")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@ApiOperation(value="주문 등록")
	@ApiImplicitParams({
		@ApiImplicitParam(name="ordersMap", value="등록할 주문 정보", dataType="Map")
	})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> registOrder(@RequestBody Map<String, Object> ordersMap) {
		if (ordersMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 등록에 실패했습니다."));
		}

		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo orders = mapper.convertValue(ordersMap.get("orders"), OrdersVo.class);
		List<BucketItemVo> ordersItemList = mapper.convertValue(ordersMap.get("ordersItemList"),
				new TypeReference<List<BucketItemVo>>() {
				});

		// 필수 사항들(주문 정보, 주문 품목 정보)에 대하여 체크한다.
		// 필수 사항 외에도 회원 주문인데 회원 번호가 없거나, 비회원인데 비밀번호가 없는 경우도 잡아낸다.
		if (orders == null || ordersItemList == null || ordersItemList.size() == 0
				|| ("Y".equals(orders.getMemberStatus()) && orders.getMemberNo() == null)
				|| ("N".equals(orders.getMemberStatus()) && orders.getPassword() == null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 등록에 실패했습니다."));
		}

		Boolean registResult = ordersService.registOrder(orders, ordersItemList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	@ApiOperation(value="회원의 주문 내역 조회", response=OrdersSummaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberNo", value="주문 내역을 조회할 회원 번호", dataType="Long", paramType="path"),
		@ApiImplicitParam(name="offset", value="주문 내역 조회 시작점", dataType="string", paramType="query"),
		@ApiImplicitParam(name="limit", value="주문 내역 조회 끝점", dataType="string", paramType="query")
	})
	@RequestMapping(value = "/members/{memberNo}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showOrdersSearchResult(
			@PathVariable Optional<Long> memberNo, 
			@RequestParam(value="offset", required=true) String offset, 
			@RequestParam(value="limit", required=true) String limit) {
		// path variable check
		if (!memberNo.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문내역 열람에 실패했습니다."));
		}
		
		List<OrdersSummaryDto> ordersList = ordersService.showOrdersByMemberNo(memberNo.get(), offset, limit);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersList));
	}

	// 비회원이 인증해야 해서 POST로 지정
	@ApiOperation(value="주문 상세 조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 주문 번호", dataType="Long", paramType="path"),
		@ApiImplicitParam(name="ordersVo", value="조회에 사용할 인증 수단", dataType="OrdersVo", paramType="body")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> showOrderDetails(@PathVariable Optional<Long> no, @RequestBody OrdersVo ordersVo) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 상세 조회에 실패했습니다."));
		}
		
		OrdersDetailsDto orderResult = ordersService.showOrdersDetails(no.get(), ordersVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderResult));
	}

	// 주문 상태 -> 취소로 변경
	@ApiOperation(value="주문 취소")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="취소할 주문 번호", dataType="Long", paramType="path"),
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrderStatusToCANCEL(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("주문 취소에 실패했습니다."));
		}
		
		Boolean modifyStatus = ordersService.modifyOrderStatusToCANCEL(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyStatus));
	}

}
