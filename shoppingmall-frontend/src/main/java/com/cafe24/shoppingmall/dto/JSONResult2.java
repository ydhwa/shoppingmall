package com.cafe24.shoppingmall.dto;

public class JSONResult2 {
	private String result;
	private String message;
	private Object data;

	public static JSONResult2 success(Object data) {
		return new JSONResult2("success", null, data);
	}

	public static JSONResult2 fail(String message) {
		return new JSONResult2("fail", message, null);
	}

	private JSONResult2(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
