package com.cafe24.shoppingmall.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.shoppingmall.config.WebConfig;
import com.cafe24.shoppingmall.exception.ExceptionMessage;
import com.cafe24.shoppingmall.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class })
@WebAppConfiguration
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	public void testMemberServiceDI() {
		assertNotNull(memberService);
	}
	
	@Test
	public void testJoinSuccess() {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		assertEquals(memberService.join(memberVo), memberVo);
	}
	@Test
	public void testJoinFailureBecauseValidation() {
		MemberVo memberVo = new MemberVo("user01", "1234", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);
		
		assertEquals(memberService.join(memberVo), ExceptionMessage.WRONG_INPUT);
	}
	@Test
	public void testJoinFailureBecauseMissingData() {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", null, "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);
		
		assertEquals(memberService.join(memberVo), ExceptionMessage.MISSING_INPUT);
	}
	
	@Test
	public void testUsernameIsNotDuplicated() {
		String username = "user02";
		
		assertEquals(memberService.checkUsername(username), username);
	}
	@Test
	public void testUsernameIsDuplicated() {
		String username = "user00";
		
		assertEquals(memberService.checkUsername(username), ExceptionMessage.DUPLICATE_USERNAME);
	}
}
