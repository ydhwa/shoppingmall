package com.cafe24.shoppingmall.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.cafe24.shoppingmall.validator.ValidPassword;

/**
 * 로그인 시 사용하는 DTO
 * 
 * @author YDH
 *
 */
public class MemberLoginDto {
	@NotBlank (message="{NotBlank.memberVo.username}")
	@Pattern(regexp="[A-Za-z0-9_]{4,12}", message="{Pattern.memberVo.username}") 
	private String username;		// 아이디
	@NotBlank(message="{NotBlank.memberVo.password}")
	@ValidPassword
	private String password;		// 비밀번호
	
	public MemberLoginDto() {}
	public MemberLoginDto(String username, String password) {
		this.username = username;
		this.password = password;
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
		return "MemberLoginDto [username=" + username + ", password=" + password + "]";
	}
}
