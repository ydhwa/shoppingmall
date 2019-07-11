package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MemberVo {
	private Long no;				// 회원번호
	@NotNull @Pattern(regexp = "^[A-Za-z0-9_]{4,12}$") @Min(4) @Max(12)
	private String username;		// 아이디
	@NotNull @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,20}$") @Min(8) @Max(20)
	private String password;		// 비밀번호
	private String regDate;			// 가입일
	@NotNull
	private String name;			// 이름
	@NotNull
	private String birthDate;		// 생년월일
	private String homeNumber;		// 유선전화번호
	@NotNull
	private String phoneNumber;		// 휴대전화번호
	@NotNull
	private String email;			// 이메일
	@NotNull
	private Boolean smsReception;	// SMS수신여부
	@NotNull
	private Boolean emailReception;	// 이메일수신여부
	private Integer savings;		// 적립금
	private MemberStatus status;	// 계정상태
	private MemberRole role;		// 권한

	public MemberVo() {}
	public MemberVo(String username, String password) { 
		this.username = username; 
		this.password = password; 
	}
	public MemberVo(String username, String password, String name, String birthDate, String homeNumber, String phoneNumber, String email, Boolean smsReception, Boolean emailReception) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.homeNumber = homeNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.smsReception = smsReception;
		this.emailReception = emailReception;
	}
	public MemberVo(Long no, String username, String password, String regDate, String name, String birthDate, String homeNumber, String phoneNumber, String email, Boolean smsReception, Boolean emailReception, Integer savings, MemberStatus status, MemberRole role) {
		this.no = no;
		this.username = username;
		this.password = password;
		this.regDate = regDate;
		this.name = name;
		this.birthDate = birthDate;
		this.homeNumber = homeNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.smsReception = smsReception;
		this.emailReception = emailReception;
		this.savings = savings;
		this.status = status;
		this.role = role;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	public Boolean getSmsReception() {
		return smsReception;
	}
	public void setSmsReception(Boolean smsReception) {
		this.smsReception = smsReception;
	}
	public Boolean getEmailReception() {
		return emailReception;
	}
	public void setEmailReception(Boolean emailReception) {
		this.emailReception = emailReception;
	}
	public Integer getSavings() {
		return savings;
	}
	public void setSavings(Integer savings) {
		this.savings = savings;
	}
	public MemberStatus getStatus() {
		return status;
	}
	public void setStatus(MemberStatus status) {
		this.status = status;
	}
	public MemberRole getRole() {
		return role;
	}
	public void setRole(MemberRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", username=" + username + ", password=" + password + ", regDate=" + regDate
				+ ", name=" + name + ", birthDate=" + birthDate + ", homeNumber=" + homeNumber + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", smsReception=" + smsReception + ", emailReception="
				+ emailReception + ", savings=" + savings + ", status=" + status.toString() + ", role=" + role.toString() + "]";
	}
	
	public enum MemberStatus {
		ENABLE("ENABLE"),
		DISABLE("DISABLE");

		private String str;
		MemberStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	public enum MemberRole {
		USER("USER"),
		ADMIN("ADMIN");

		private String str;
		MemberRole(String str) { this.str = str; }
		public String toString() { return str; }
	}
}