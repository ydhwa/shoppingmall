package com.cafe24.shoppingmall.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final static String ACCESS_TOKEN = "07d59760-3bb8-4a4e-9f3e-451ea6a9861d";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto member = userDao.get(username);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(member != null) {
			securityUser.setNo(member.getNo());
			securityUser.setName(member.getName());
			securityUser.setUsername(member.getUsername());
			securityUser.setPassword(member.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(member.getRole())));
		}
		
		return securityUser;
	}	
}