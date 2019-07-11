package com.cafe24.shoppingmall.exception;

public class VoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public VoException() {
		super("VoExcpetion Occurs");
	}
	
	public VoException(String message) {
		super(message);
	}
}
