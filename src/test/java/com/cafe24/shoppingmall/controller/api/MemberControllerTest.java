package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
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
import com.cafe24.shoppingmall.exception.Message;
import com.cafe24.shoppingmall.vo.MemberCheckDuplicateVo;
import com.cafe24.shoppingmall.vo.MemberJoinVo;
import com.cafe24.shoppingmall.vo.MemberLoginVo;
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
		MemberJoinVo memberVo = new MemberJoinVo("user01", "asdf1234!", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.username", is(memberVo.getUsername())))
				.andExpect(jsonPath("$.data.name", is(memberVo.getName())));
	}
	@Test
	public void testJoinFailureBecauseInvalidData() throws Exception {
		MemberJoinVo memberVo = new MemberJoinVo("user01", "1234", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
	@Test
	public void testJoinFailureBecauseMissingData() throws Exception {
		MemberJoinVo memberVo = new MemberJoinVo("user01", "asdf1234!", null, "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());

	}
	
	@Test
	public void testCheckUsernameSuccessWithNotDuplicatedUsername() throws Exception {
		MemberCheckDuplicateVo memberVo = new MemberCheckDuplicateVo("user02");

		ResultActions resultActions = mockMvc.perform(
				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.USERNAME_UNIQUE.toString())));
	}
	@Test
	public void testCheckUsernameSuccessWithDuplicatedUsername() throws Exception {
		MemberCheckDuplicateVo memberVo = new MemberCheckDuplicateVo("user");

		ResultActions resultActions = mockMvc.perform(
				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(Message.USERNAME_DUPLICATED.toString())));
	}
	@Test
	public void testCheckUsernameFailureBecauseInvalidData() throws Exception {
		MemberCheckDuplicateVo memberVo = new MemberCheckDuplicateVo("user02**@");

		ResultActions resultActions = mockMvc.perform(
				get("/api/member/username").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
	
	@Test
	public void testLoginSuccessWithMatchingAccount() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("user", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.LOGIN_SUCCESS.toString())));
	}
	@Test
	public void testLoginSuccessWithNotMatchingAccount() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("testuser", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.LOGIN_FAILURE.toString())));
	}
	@Test
	public void testLoginFailureBecauseInvalidData() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("user**", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}
