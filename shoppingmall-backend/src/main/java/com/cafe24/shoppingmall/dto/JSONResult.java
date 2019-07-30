package com.cafe24.shoppingmall.dto;

/**
 * JSON으로 정형화된 성공/실패 데이터를 주고받기 위한 클래스
 * 
 * @author YDH
 *
 */
public class JSONResult {
	private String result;	// success, fail
	private String message;	// if fail, set
	private Object data;	// if success, set
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	public static JSONResult failure(String message) {
		return new JSONResult("failure", message, null);
	}
	
	private JSONResult(String result, String message, Object data) {
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
