package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.User;
import com.cafe24.shoppingmall.vo.MemberVo;

@Service
public class UserService {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;

	public Boolean join(MemberVo memberVo) {
		String endpoint = "http://localhost:8888/api/members";
		JSONResultJoin result = restTemplate.postForObject(endpoint, memberVo, JSONResultJoin.class);
		
		return result.getData();
	}
	
	public List<User> getUserList(int startIndex, int productPerPage, HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/admin/members?offset=%d&limit=%d", startIndex, productPerPage);
		
		// 별도의 검색 조건은 세팅하지 않음
		
		JSONResultUserList result = restTemplate.getForObject(endpoint, JSONResultUserList.class);
		
		return result.getData();
	}
	
	private static class JSONResultJoin extends JSONResult<Boolean> {
	}
	private static class JSONResultUserList extends JSONResult<List<User>> {
	}
 }
