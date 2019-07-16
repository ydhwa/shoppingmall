package com.cafe24.shoppingmall.vo;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cafe24.shoppingmall.validator.ValidPassword;
import com.cafe24.shoppingmall.validator.ValidPhoneNumber;
import com.cafe24.shoppingmall.validator.ValidUsername;
import com.cafe24.shoppingmall.validator.groups.MemberGroups;

/**
 * 고객관리 시 사용하는 회원 VO
 * 
 * @author YDH
 *
 */
public class MemberVo {
	private Long no;				// 회원번호
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Login.class}, message="아이디는 필수 입력 항목입니다.")
	@ValidUsername(groups={MemberGroups.Join.class, MemberGroups.Login.class}) 
	private String username;		// 아이디
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Login.class}, message="비밀번호는 필수 입력 항목입니다.")
	@ValidPassword(groups={MemberGroups.Join.class, MemberGroups.Login.class})
	private String password;		// 비밀번호
	private String regDate;			// 가입일
	@NotBlank(groups={MemberGroups.Join.class}, message="이름은 필수 입력 항목입니다.")
	private String name;			// 이름
	@NotBlank(groups={MemberGroups.Join.class}, message="생년월일은 필수 입력 항목입니다.")
	private String birthDate;		// 생년월일
	private String homeNumber;		// 유선전화번호
	@NotBlank(groups={MemberGroups.Join.class}, message="휴대전화번호는 필수 입력 항목입니다.")
	@ValidPhoneNumber(groups={MemberGroups.Join.class}) 
	private String phoneNumber;		// 휴대전화번호
	@NotBlank(groups={MemberGroups.Join.class}, message="이메일은 필수 입력 항목입니다.")
	@Email(groups={MemberGroups.Join.class}, message="이메일 형식이 아닙니다.")
	private String email;			// 이메일
	private MemberStatus status;	// 계정상태
	private MemberRole role;		// 권한
	
	private List<ShippingAddressManageVo> shippingAddressList;
	private List<BucketVo> bucketList;
	private List<OrdersVo> ordersList;

	public MemberVo() {}
	public MemberVo(String username, String password, String name, String birthDate, String homeNumber, String phoneNumber, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.homeNumber = homeNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public MemberVo(Long no, String username, String password, String regDate, String name, String birthDate, String homeNumber, String phoneNumber, String email, MemberStatus status, MemberRole role) {
		this.no = no;
		this.username = username;
		this.password = password;
		this.regDate = regDate;
		this.name = name;
		this.birthDate = birthDate;
		this.homeNumber = homeNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
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
	public List<ShippingAddressManageVo> getShippingAddressList() {
		return shippingAddressList;
	}
	public void setShippingAddressList(List<ShippingAddressManageVo> shippingAddressList) {
		this.shippingAddressList = shippingAddressList;
	}
	public List<BucketVo> getBucketList() {
		return bucketList;
	}
	public void setBucketList(List<BucketVo> bucketList) {
		this.bucketList = bucketList;
	}
	public List<OrdersVo> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<OrdersVo> ordersList) {
		this.ordersList = ordersList;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", username=" + username + ", password=" + password + ", regDate=" + regDate
				+ ", name=" + name + ", birthDate=" + birthDate + ", homeNumber=" + homeNumber + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", status=" + status + ", role=" + role + ", shippingAddressList="
				+ shippingAddressList + ", bucketList=" + bucketList + ", ordersList=" + ordersList + "]";
	}

	/**
	 * 고객 상태에 대한 enum 클래스
	 * ENABLE(활성)/DISABLE(비활성)
	 * 
	 * @author YDH
	 *
	 */
	public enum MemberStatus {
		ENABLE("ENABLE"),
		DISABLE("DISABLE");

		private String str;
		MemberStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	/**
	 * 고객 권한에 대한 enum 클래스
	 * USER(유저)/ADMIN(관리자)
	 * 
	 * @author YDH
	 *
	 */
	public enum MemberRole {
		USER("USER"),
		ADMIN("ADMIN");

		private String str;
		MemberRole(String str) { this.str = str; }
		public String toString() { return str; }
	}
}