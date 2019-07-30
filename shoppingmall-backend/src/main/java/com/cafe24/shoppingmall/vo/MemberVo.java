package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cafe24.shoppingmall.validator.ValidPassword;
import com.cafe24.shoppingmall.validator.ValidPhoneNumber;
import com.cafe24.shoppingmall.validator.ValidUsername;
import com.cafe24.shoppingmall.validator.groups.MemberGroups;
import com.cafe24.shoppingmall.vo.Enum.MemberStatus;

/**
 * 회원에 대한 VO
 * 
 * @author YDH
 *
 */
public class MemberVo {
	@NotNull(groups={MemberGroups.Modify.class}, message="회원번호는 필수적으로 포함되어야 합니다.")
	private Long no;				// 회원번호
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Login.class}, message="아이디는 필수 입력 항목입니다.")
	@ValidUsername(groups={MemberGroups.Join.class, MemberGroups.Login.class}) 
	private String username;		// 아이디
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Login.class, MemberGroups.Modify.class}, message="비밀번호는 필수 입력 항목입니다.")
	@ValidPassword(groups={MemberGroups.Join.class, MemberGroups.Login.class, MemberGroups.Modify.class})
	private String password;		// 비밀번호
	private String regDate;			// 가입일
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Modify.class}, message="이름은 필수 입력 항목입니다.")
	private String name;			// 이름
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Modify.class}, message="생년월일은 필수 입력 항목입니다.")
	private String birthDate;		// 생년월일
	private String homeNumber;		// 유선전화번호
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Modify.class}, message="휴대전화번호는 필수 입력 항목입니다.")
	@ValidPhoneNumber(groups={MemberGroups.Join.class, MemberGroups.Modify.class}) 
	private String phoneNumber;		// 휴대전화번호
	@NotBlank(groups={MemberGroups.Join.class, MemberGroups.Modify.class}, message="이메일은 필수 입력 항목입니다.")
	@Email(groups={MemberGroups.Join.class, MemberGroups.Modify.class}, message="이메일 형식이 아닙니다.")
	private String email;			// 이메일
	private MemberStatus status;	// 계정상태
	private String delStatus;		// 삭제여부(Y/N)

	public MemberVo() {}

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
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", username=" + username + ", password=" + password + ", regDate=" + regDate
				+ ", name=" + name + ", birthDate=" + birthDate + ", homeNumber=" + homeNumber + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", status=" + status + ", delStatus=" + delStatus + "]";
	}
}