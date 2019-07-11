package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.WebConfig;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class })
@WebAppConfiguration
public class MemberControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testJoinSuccess() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.username", is(memberVo.getUsername())))
				.andExpect(jsonPath("$.data.name", is(memberVo.getName())));
	}
	@Test
	public void testJoinFailureBecauseInvalidData() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "1234", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
	@Test
	public void testJoinFailureBecauseMissingData() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", null, "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());

	}
	
//	public void testCheckUsernameSuccessWithNotDuplicatedUsername() throws Exception {
//		String username = "user02";
//
//		ResultActions resultActions = mockMvc.perform(
//				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(username));
//
//		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
//				.andExpect(jsonPath("$.data", is(username)));
//	}
//	public void testCheckUsernameSuccessWithDuplicatedUsername() throws Exception {
//		String username = "user";
//
//		ResultActions resultActions = mockMvc.perform(
//				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(username));
//
//		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("failure")))
//		.andExpect(jsonPath("$.data", is(username)));
//	}
//	public void testCheckUsernameFailureBecauseInvalidData() throws Exception {
//		String username = "user02**@";
//
//		ResultActions resultActions = mockMvc.perform(
//				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(username));
//
//		resultActions.andExpect(status().isBadRequest()).andDo(print());
//	}
//	
//	@Test
//	public void testLoginSuccessWithExistingAccount() throws Exception {
//		MemberVo memberVo = new MemberVo("user", "asdf1234!");
//
//		ResultActions resultActions = mockMvc.perform(
//				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));
//
//		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
//				.andExpect(jsonPath("$.data", is("ok")));
//	}
//	@Test
//	public void testLoginSuccessWithNonExistingAccount() throws Exception {
//		MemberVo memberVo = new MemberVo("user", "asdf1234!");
//
//		ResultActions resultActions = mockMvc.perform(
//				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));
//
//		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
//				.andExpect(jsonPath("$.data", is("ok")));
//	}
//	@Test
//	public void testLoginFailureBecauseInvalidData() throws Exception {
//		MemberVo memberVo = new MemberVo("user", "asdf1234!");
//
//		ResultActions resultActions = mockMvc.perform(
//				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));
//
//		resultActions.andExpect(status().isBadRequest()).andDo(print());
//	}
}
