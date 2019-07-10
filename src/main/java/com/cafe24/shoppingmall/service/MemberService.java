package com.cafe24.shoppingmall.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.exception.ExceptionMessage;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.cafe24.shoppingmall.vo.MemberVo.MemberRole;
import com.cafe24.shoppingmall.vo.MemberVo.MemberStatus;

@Service
public class MemberService {
//	private MemberVo memberVo = new MemberVo("user00", "asdf1234!", "유저", "1900-01-01", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

	public Object join(MemberVo memberVo) {
		if(!checkValidation(memberVo)) {
			return ExceptionMessage.WRONG_INPUT.toString();
		}
		
		if(!checkMissingData(memberVo)) {
			return ExceptionMessage.MISSING_INPUT.toString();
		}
		
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setSavings(0);
		memberVo.setStatus(MemberStatus.ENABLE);
		memberVo.setRole(MemberRole.USER);
		
		// Dao 필요
		
		return memberVo;
	}
	private boolean checkValidation(MemberVo memberVo) {
		// 아이디) 4~12자 영문, 숫자, _(언더바) 자유 조합
		String usernamePattern = "^[A-Za-z0-9_]{4,12}$";
		// 비밀번호) 8~20자 영문, 숫자, 특수문자 1회 이상 포함
		String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$";
		
		// 아이디 검사
		if(!memberVo.getUsername().matches(usernamePattern)) { return false; }
		
		// 비밀번호 검사
		if(!memberVo.getPassword().matches(passwordPattern)) { return false; }
		
		return true;
	}
	private boolean checkMissingData(MemberVo memberVo) {
		if(memberVo.getUsername() == null) { return false; }
		if(memberVo.getPassword() == null) { return false; }
		if(memberVo.getName() == null) { return false; }
		if(memberVo.getBirthDate() == null) { return false; }
		if(memberVo.getPhoneNumber() == null) { return false; }
		if(memberVo.getEmail() == null) { return false; }
		if(memberVo.getSmsReception() == null) { return false; }
		if(memberVo.getEmailReception() == null) { return false; }
				
		return true;
	}
	
	public Object checkUsername(String username) {
		// Dao 필요
		
		return username;
	}

}
