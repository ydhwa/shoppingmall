package com.cafe24.shoppingmall.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.shoppingmall.config.WebConfig;

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

}
