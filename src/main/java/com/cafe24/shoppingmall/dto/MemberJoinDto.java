package com.cafe24.shoppingmall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.cafe24.shoppingmall.validator.ValidPassword;

/**
 * 회원가입 시 사용하는 DTO
 * 
 * @author YDH
 *
 */
public class MemberJoinDto {
	private Long no;				// 회원번호
	@NotBlank 
	@Pattern(regexp="[A-Za-z0-9_]{4,12}") 
	private String username;		// 아이디
	@NotBlank(message="{NotBlank.memberVo.password}")
	@ValidPassword(message="{PasswordPattern.memberVo.password}")
	private String password;		// 비밀번호
	private String regDate;			// 가입일
	@NotBlank
	private String name;			// 이름
	@NotBlank
	private String birthDate;		// 생년월일
	private String homeNumber;		// 유선전화번호
	@NotBlank
	@Pattern(regexp="[0-9]{3}-[0-9]{4}-[0-9]{4}") 
	private String phoneNumber;		// 휴대전화번호
	@NotBlank
	@Email
	private String email;			// 이메일
}
