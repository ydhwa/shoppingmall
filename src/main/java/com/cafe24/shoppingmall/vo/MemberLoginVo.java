package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class MemberLoginVo {
	@NotBlank(message="아이디는 필수 입력 항목입니다.") 
	@Pattern(regexp="[A-Za-z0-9_]{4,12}", message="아이디는 4자 이상 12자 이하의 알파벳, 숫자, _(언더바)를 이용하여 작성해야 합니다.") 
	@Length(min=4, max=12, message="아이디는 4자 이상 12자 이하로 입력해야 합니다.")
	private String username;		// 아이디
	@NotBlank(message="비밀번호는 필수 입력 항목입니다.") 
	@Pattern(regexp="(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}", message="비밀번호는 8자 이상 20자 이하의 알파벳, 숫자, 특수문자를 조합하여 작성해야 합니다.") 
	@Length(min=8, max=20, message="비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
	private String password;		// 비밀번호
	
	public MemberLoginVo() {}
	public MemberLoginVo(String username, String password) { 
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
		return "MemberLoginVo [username=" + username + ", password=" + password + "]";
	}
}