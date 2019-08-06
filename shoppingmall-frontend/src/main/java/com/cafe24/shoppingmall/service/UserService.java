package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.JSONResult;
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
	
	private static class JSONResultJoin extends JSONResult<Boolean> {
	}
}
