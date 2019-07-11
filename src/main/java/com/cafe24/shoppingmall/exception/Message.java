package com.cafe24.shoppingmall.exception;

// 예외상황 메시지들에 대한 enum class
public enum Message {
	USERNAME_DUPLICATED("duplicated"),
	USERNAME_UNIQUE("unique");
	
	private String str;
	Message(String str) { this.str = str; }
	public String toString() { return str; }
}
