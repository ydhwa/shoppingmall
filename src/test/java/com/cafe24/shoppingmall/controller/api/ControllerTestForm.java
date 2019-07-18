package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ControllerTestForm {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@AfterClass
	@Rollback(true)
	public static void cleanUp() {}
	
	@Test
	public void testJoinSuccessInsertSuccess() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userB01");
		memberVo.setPassword("asdf1234!");
		memberVo.setName("회원B");
		memberVo.setBirthDate("1990-01-01");
		memberVo.setHomeNumber("333-333-3333");
		memberVo.setPhoneNumber("333-3333-3333");
		memberVo.setEmail("userB01@test.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/members").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
	}
	
	@Test
	public void testCheckUsernameDuplicationSuccessWithNotDuplicatedUsername() throws Exception {
		String username = "userB02";

		ResultActions resultActions = mockMvc.perform(get("/api/members/username/{username}", username).accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(false)));
	}
}
