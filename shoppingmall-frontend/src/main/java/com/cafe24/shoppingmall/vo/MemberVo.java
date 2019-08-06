package com.cafe24.shoppingmall.vo;

/**
 * 회원가입 시 사용하는 VO
 * 
 * @author YDH
 *
 */
public class MemberVo {
	private String username;		// 아이디
	private String password;		// 비밀번호
	private String name;			// 이름
	private String birthDate;		// 생년월일
	private String homeNumber;		// 유선전화번호
	private String phoneNumber;		// 휴대전화번호
	private String email;			// 이메일

	public MemberVo() {}

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberVo [username=" + username + ", password=" + password + ", name=" + name + ", birthDate="
				+ birthDate + ", homeNumber=" + homeNumber + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
}