package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.ProductDetails;
import com.cafe24.shoppingmall.dto.ProductSummary;

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
		JSONResultProductRegist result = restTemplate.postForObject(endpoint, paramMap, JSONResultProductRegist.class); 
		
		return result.getData();
	}
	
	public ProductDetails getProductByNoAsAdmin(Long no) {
		String endpoint = "http://localhost:8888/api/admin/products/" + no;
		JSONResultProduct result = restTemplate.getForObject(endpoint, JSONResultProduct.class);
		
		return result.getData();
	}

	
	private static class JSONResultProductList extends JSONResult<List<ProductSummary>> {
	}
	private static class JSONResultProduct extends JSONResult<ProductDetails> {
	}
	private static class JSONResultProductRegist extends JSONResult<Boolean> {
	}
}
