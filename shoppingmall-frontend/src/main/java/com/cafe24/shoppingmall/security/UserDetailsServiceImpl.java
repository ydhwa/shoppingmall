package com.cafe24.shoppingmall.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.Member;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {
		String endpoint = "http://localhost:8888/api/members/security/" + username;
		JSONResultMember jsonResult = restTemplate.getForObject(endpoint, JSONResultMember.class);
		
		Member member = jsonResult.getData();
		SecurityUser securityUser = new SecurityUser();

		if (member != null) {
			securityUser.setNo(member.getNo());
			securityUser.setName(member.getName());
			securityUser.setUsername(member.getUsername());
			securityUser.setPassword(member.getPassword());

			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for(String role: member.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			securityUser.setAuthorities(authorities);
		}

		return securityUser;
	}
	
	private static class JSONResultMember extends JSONResult<Member> {
	}
}