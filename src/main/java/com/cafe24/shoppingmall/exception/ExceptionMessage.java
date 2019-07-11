package com.cafe24.shoppingmall.exception;

// 예외상황 메시지들에 대한 enum class
public enum ExceptionMessage {
	INVALID_DATA("invalid data"),
	MISSING_DATA("missing data"),
	DUPLICATE_USERNAME("username is duplicated");
	
	private String str;
	ExceptionMessage(String str) { this.str = str; }
	public String toString() { return str; }
}
