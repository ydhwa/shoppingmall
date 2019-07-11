package com.cafe24.shoppingmall.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.shoppingmall.config.WebConfig;
import com.cafe24.shoppingmall.exception.Message;
import com.cafe24.shoppingmall.vo.MemberJoinVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class })
@WebAppConfiguration
public class MemberServiceTest {
	// Dao를 호출하는 비즈니스 로직 검증

	@Autowired
	private MemberService memberService;
	
	@Test
	public void testMemberServiceDI() {
		assertNotNull(memberService);
	}
	
	@Test
	public void testJoinSuccess() {
		MemberJoinVo memberVo = new MemberJoinVo("user01", "asdf1234!", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		assertEquals(memberService.join(memberVo), memberVo);
	}
}
