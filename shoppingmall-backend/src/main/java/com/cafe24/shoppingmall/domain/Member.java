package com.cafe24.shoppingmall.domain;

public class Member {
	private Long no;
	private String username;
	private String password;
	public Member() {}
	public Member(Long no, String username, String password) {
		this.no = no;
		this.username = username;
		this.password = password;
	}
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
	@Override
	public String toString() {
		return "Member [no=" + no + ", username=" + username + ", password=" + password + "]";
	}
}
