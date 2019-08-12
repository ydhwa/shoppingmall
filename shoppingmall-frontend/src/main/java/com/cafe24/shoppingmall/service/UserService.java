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
		JSONResultBoolean result = restTemplate.postForObject(endpoint, memberVo, JSONResultBoolean.class);
		
		return result.getData();
	}
	
	public List<User> getUserList(int startIndex, int productPerPage, HashMap<String, String> paramMap) {
		String endpoint = String.format("http://localhost:8888/api/admin/members?offset=%d&limit=%d", startIndex, productPerPage);
		
		// 별도의 검색 조건은 세팅하지 않음
		
		JSONResultUserList result = restTemplate.getForObject(endpoint, JSONResultUserList.class);
		
		return result.getData();
	}
	
	public Boolean existUsername(String username) {
		String endpoint = "http://localhost:8888/api/members/duplicate?username=" + username;
		JSONResultBoolean result = restTemplate.getForObject(endpoint, JSONResultBoolean.class);
		
		return result.getData();
	}
	
	public Long getUserNo(String username) {
		String endpoint = "http://localhost:8888/api/members/security/" + username;
		JSONResultUserNo result = restTemplate.getForObject(endpoint, JSONResultUserNo.class);
		
		return result.getData().getNo();
	}
	
	private static class JSONResultBoolean extends JSONResult<Boolean> {
	}
	private static class JSONResultUserList extends JSONResult<List<User>> {
	}
	private static class JSONResultUserNo extends JSONResult<User> {
	}
 }
