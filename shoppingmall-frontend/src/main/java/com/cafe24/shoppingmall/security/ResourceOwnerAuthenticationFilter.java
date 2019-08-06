package com.cafe24.shoppingmall.security;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Resource Owner Form Login Filter
 * @author yun-yeoseong
 *
 */
public class ResourceOwnerAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private boolean postOnly = true;

	public ResourceOwnerAuthenticationFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		/*
		 * POST로 넘어왔는지 체크
		 */
		if(postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if(StringUtils.isEmpty(username)) {
			username = "";
		}
		if(StringUtils.isEmpty(password)) {
			password = "";
		}

		username = username.trim();
		password = getSha512(password);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	// SHA-512로 암호화
	private static String getSha512(String pwd) {
		StringBuilder sb = new StringBuilder("");

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			byte[] bytes = md.digest(pwd.getBytes("UTF-8"));

			for(int i = 0; i < bytes.length; i++) {
				String hexString = Integer.toHexString(bytes[i] & 0xff);
				while(hexString.length() < 2) {
					hexString = "0" + hexString;
				}
				sb.append(hexString);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
