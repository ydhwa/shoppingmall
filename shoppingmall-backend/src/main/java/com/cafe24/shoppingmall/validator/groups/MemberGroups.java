package com.cafe24.shoppingmall.validator.groups;

/**
 * 상황에 따라 적용시켜야 할 Validator가 다르므로 작성한 Group 인터페이스
 * 
 * @author YDH
 *
 */
public interface MemberGroups {
	interface Join { }		// 회원가입 시
	interface Login { }		// 로그인 시
	interface Modify { }	// 회원정보 수정 시
}
