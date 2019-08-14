package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.dto.ProductDetails;
import com.cafe24.shoppingmall.dto.ProductSummary;
import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.OrdersVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;

@Service
public class ProductService {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	public List<ProductSummary> getProductList(int startIndex, int productPerPage, HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/products?offset=%d&limit=%d", startIndex, productPerPage);
		
		// 여러 검색 조건이 있지만 일단 categoryNo만 검색 조건을 만든다.
		if(paramMap.containsKey("categoryNo")) {
			endpoint += "&categoryNo=" + paramMap.get("categoryNo");
		}
		
		JSONResultProductList result = restTemplate.getForObject(endpoint, JSONResultProductList.class);
		
		return result.getData();
	}
	
	public ProductDetails getProductByNo(Long no) {
		String endpoint = "http://localhost:8888/api/products/" + no;
		JSONResultProduct result = restTemplate.getForObject(endpoint, JSONResultProduct.class);
		
		return result.getData();
	}
	
	public List<ProductSummary> getProductListAsAdmin(int startIndex, int productPerPage, HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/admin/products?offset=%d&limit=%d", startIndex, productPerPage);
		
		// 검색 조건 미생성
		
		JSONResultProductList result = restTemplate.getForObject(endpoint, JSONResultProductList.class);
		
		return result.getData();
	}
	
	public Boolean registProduct(Map<String, Object> paramMap) {
		String endpoint = "http://localhost:8888/api/admin/products";
		JSONResultBoolean result = restTemplate.postForObject(endpoint, paramMap, JSONResultBoolean.class); 
		
		return result.getData();
	}
	
	public ProductDetails getProductByNoAsAdmin(Long no) {
		String endpoint = "http://localhost:8888/api/admin/products/" + no;
		JSONResultProduct result = restTemplate.getForObject(endpoint, JSONResultProduct.class);
		
		return result.getData();
	}
	
	public ProductOptionItemVo getProductItem(HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/products/items?productNo=%s&optionValueKeys=%s", paramMap.get("productNo"), paramMap.get("optionValueKeys"));
		JSONResultProductOptoinItem result = restTemplate.getForObject(endpoint, JSONResultProductOptoinItem.class);
		
		return result.getData();
	}
	
	// 장바구니 등록
	public Boolean registBucket(Map<String, Object> bucketMap) {
		String endpoint = "http://localhost:8888/api/buckets";
		JSONResultBoolean result = restTemplate.postForObject(endpoint, bucketMap, JSONResultBoolean.class); 
		
		return result.getData();
	}
	// 장바구니 조회
	public List<BucketItem> getMemberBucketList(Long memberNo) {
		String endpoint = "http://localhost:8888/api/buckets/members/" + memberNo;
		JSONResultBucketList result = restTemplate.getForObject(endpoint, JSONResultBucketList.class);
		
		return result.getData();
	}
	public List<BucketItem> getNonMemberBucketList(String identifier) {
		String endpoint = "http://localhost:8888/api/buckets/non-members/" + identifier;
		JSONResultBucketList result = restTemplate.getForObject(endpoint, JSONResultBucketList.class);
		
		return result.getData();
	}
	// 장바구니에 담긴 물품들의 총 금액 조회
	public Integer getBucketToalPrice(Long memberNo, String identifier) {
		String endpoint = "http://localhost:8888/api/buckets/price?";
		if(memberNo != null) {
			endpoint += "memberNo=" + memberNo;
		} else {
			endpoint += "identifier=" + identifier;
		}
		JSONResultInteger result = restTemplate.getForObject(endpoint, JSONResultInteger.class);

		return result.getData();
	}
	// 장바구니 수량 수정
	public Boolean modifyBucket(BucketItemVo bucketItemVo) {
		String endpoint = "http://localhost:8888/api/buckets";
		
		HttpEntity<BucketItemVo> requestEntity = new HttpEntity<>(bucketItemVo);
		ResponseEntity<JSONResultBoolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, requestEntity, JSONResultBoolean.class);
		
		return responseEntity.getBody().getData();
	}
	// 장바구니 삭제
	public Boolean deleteBucketList(List<BucketItemVo> bucketList) {
		String endpoint = "http://localhost:8888/api/buckets";
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(bucketList);
		ResponseEntity<JSONResultBoolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.DELETE, requestEntity, JSONResultBoolean.class);
		
		return responseEntity.getBody().getData();
	}
	
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
	public OrdersDetailsDto getOrderAsNonMember(OrdersVo orderVo) {
		String endpoint = "http://localhost:8888/api/orders/" + 0;
		JSONResultOrderDetailsDto result = restTemplate.postForObject(endpoint, orderVo, JSONResultOrderDetailsDto.class);
		
		return result.getData();
	}
	
	private static class JSONResultProductList extends JSONResult<List<ProductSummary>> {
	}
	private static class JSONResultProduct extends JSONResult<ProductDetails> {
	}
	private static class JSONResultBoolean extends JSONResult<Boolean> {
	}
	private static class JSONResultProductOptoinItem extends JSONResult<ProductOptionItemVo> {
	}
	private static class JSONResultBucketList extends JSONResult<List<BucketItem>> {
	}
	private static class JSONResultInteger extends JSONResult<Integer> {
	}
	private static class JSONResultString extends JSONResult<String> {
	}
	private static class JSONResultOrderSummaryListDto extends JSONResult<List<OrdersSummaryDto>> {
	}
	private static class JSONResultOrderDetailsDto extends JSONResult<OrdersDetailsDto> {
	}
}
