package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.Category;
import com.cafe24.shoppingmall.dto.JSONResult;

@Service
public class CategoryService {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;

	public List<Category> getAllCategories() {
		
		String endpoint = "http://localhost:8888/api/categories";
		JSONResultCategoryList result = restTemplate.getForObject(endpoint, JSONResultCategoryList.class);
		
		return result.getData();
	}

	private static class JSONResultCategoryList extends JSONResult<List<Category>> {
	}
}
