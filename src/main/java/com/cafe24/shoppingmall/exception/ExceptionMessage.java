package com.cafe24.shoppingmall.exception;

// 예외상황 메시지들에 대한 enum class
public enum ExceptionMessage {
	INVALID_INPUT("invalid input"),
	MISSING_INPUT("missing input"),
	DUPLICATE_USERNAME("ID(username) is duplicated");
	
	private String str;
	ExceptionMessage(String str) { this.str = str; }
	public String toString() { return str; }
}
