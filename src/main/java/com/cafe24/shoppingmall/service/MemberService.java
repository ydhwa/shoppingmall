package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.exception.ExceptionMessage;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.cafe24.shoppingmall.vo.MemberVo.MemberRole;
import com.cafe24.shoppingmall.vo.MemberVo.MemberStatus;

@Service
public class MemberService {
//	private MemberVo memberVo = new MemberVo("user00", "asdf1234!", "유저", "1900-01-01", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

	public Object join(MemberVo memberVo) {
		
		// 남은 데이터 등록(회원번호, 등록일은 데이터베이스 단에서 처리)
		memberVo.setSavings(0);
		memberVo.setStatus(MemberStatus.ENABLE);
		memberVo.setRole(MemberRole.USER);
		
		// Dao 필요
		
		return memberVo;
	}
	
	public Object checkUsername(String username) {
		// Dao 필요
		
		return username;
	}
	public Object login(MemberVo memberVo) {
		// null값 처리
		if(memberVo.getUsername() == null) { memberVo.setUsername(""); }
		if(memberVo.getPassword() == null) { memberVo.setPassword(""); }
		
		
		
		// Dao 필요
		
		return null;
	}

}
