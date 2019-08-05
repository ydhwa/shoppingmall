package com.cafe24.shoppingmall.dto;

import java.util.Arrays;

public class MemberDto {
	private Long no;
	private String username;
	private String password;
	private String name;
	private Object[] authorities;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Object[] authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", authorities=" + Arrays.toString(authorities) + "]";
	}
}
