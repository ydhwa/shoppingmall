package com.cafe24.shoppingmall.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.cafe24.shoppingmall.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private final static String ACCESS_TOKEN = "07d59760-3bb8-4a4e-9f3e-451ea6a9861d";

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			Request request = new Request
					.Builder()
					.url("http://localhost:8888/api/members/security/" + username)
					.addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
					.get()
					.build();
			OkHttpClient client = new OkHttpClient();
			Response response = client.newCall(request).execute();

			ObjectMapper mapper = new ObjectMapper();
			MemberDto member = mapper.readValue(response.body().string(), MemberDto.class);
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
				
				return securityUser;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
}