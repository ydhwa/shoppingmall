package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.vo.OrdersVo;

@Service
public class OrderService {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	// 주문 등록
	public String registOrder(Map<String, Object> paramMap) {
		String endpoint = "http://localhost:8888/api/orders";
		JSONResultString result = restTemplate.postForObject(endpoint, paramMap, JSONResultString.class); 
		
		return result.getData();
	}
	// 관리자 주문 목록 조회
	public List<OrdersSummaryDto> getOrderListAsAdmin(int startIndex, int productPerPage, HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/admin/orders?offset=%d&limit=%d", startIndex, productPerPage);
		
		// 검색 조건 미생성
		
		JSONResultOrderSummaryListDto result = restTemplate.getForObject(endpoint, JSONResultOrderSummaryListDto.class);
		
		return result.getData();
	}
	// 관리자 주문 상세 조회
	public OrdersDetailsDto getOrderByNoAsAdmin(Long no) {
		String endpoint = "http://localhost:8888/api/admin/orders/" + no;
		JSONResultOrderDetailsDto result = restTemplate.getForObject(endpoint, JSONResultOrderDetailsDto.class);
		
		return result.getData();
	}
	
	// 비회원 주문 상세 조회
	public OrdersDetailsDto getOrderAsNonMember(OrdersVo orderVo) {
		String endpoint = "http://localhost:8888/api/orders/" + 0;
		JSONResultOrderDetailsDto result = restTemplate.postForObject(endpoint, orderVo, JSONResultOrderDetailsDto.class);
		
		return result.getData();
	}
	
	// 회원 주문 목록 조회
	public List<OrdersSummaryDto> getOrderList(Long no, int startPage, int productPerPage) {
		String endpoint = "http://localhost:8888/api/orders/members/" + no + "?offset=" + startPage + "&limit=" + productPerPage;
		JSONResultOrderSummaryListDto result = restTemplate.getForObject(endpoint, JSONResultOrderSummaryListDto.class);
		
		return result.getData();
	}
	// 회원 주문 상세 조회
	public OrdersDetailsDto getOrderAsMember(Long no, OrdersVo orderVo) {
		String endpoint = "http://localhost:8888/api/orders/" + no;
		orderVo.setMemberStatus("Y");
		JSONResultOrderDetailsDto result = restTemplate.postForObject(endpoint, orderVo, JSONResultOrderDetailsDto.class);
		
		return result.getData();
	}
	
	private static class JSONResultString extends JSONResult<String> {
	}
	private static class JSONResultOrderSummaryListDto extends JSONResult<List<OrdersSummaryDto>> {
	}
	private static class JSONResultOrderDetailsDto extends JSONResult<OrdersDetailsDto> {
	}
}
