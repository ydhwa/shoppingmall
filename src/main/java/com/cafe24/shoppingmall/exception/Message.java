package com.cafe24.shoppingmall.exception;

// 상태 메시지들에 대한 enum class
public enum Message {
	USERNAME_DUPLICATED("username is duplicated"),
	USERNAME_UNIQUE("username is unique"),
	
	LOGIN_SUCCESS("login success"),
	LOGIN_FAILURE("login failure");
	
	private String str;
	Message(String str) { this.str = str; }
	public String toString() { return str; }
}
