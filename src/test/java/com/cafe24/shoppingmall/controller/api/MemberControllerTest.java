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
import com.cafe24.shoppingmall.vo.MemberLoginVo;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.google.gson.Gson;

/**
 * 회원 컨트롤러 동작에 대한 테스트
 * 
 * @author YDH
 *
 */
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

	/**
	 * 회원가입 성공
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testJoinSuccess() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.username", is(memberVo.getUsername())))
				.andExpect(jsonPath("$.data.name", is(memberVo.getName())));
	}
	/**
	 * 정규식에서 벗어나는 데이터로 인해 회원가입 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testJoinFailureBecauseInvalidData() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "1234", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
	/**
	 * 필수 입력 사항을 입력하지 않아 회원가입 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testJoinFailureBecauseMissingData() throws Exception {
		MemberVo memberVo = new MemberVo("user01", "asdf1234!", null, "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());

	}
	
	/**
	 * 아이디 중복체크 - 중복되는 닉네임이 없음
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckUsernameSuccessWithNotDuplicatedUsername() throws Exception {
		String username = "user02";

		ResultActions resultActions = mockMvc.perform(get("/api/member/username/{username}", username).accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.USERNAME_UNIQUE.toString())));
	}
	/**
	 * 아이디 중복체크 - 중복되는 닉네임이 있음
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckUsernameSuccessWithDuplicatedUsername() throws Exception {
		String username = "user";

		ResultActions resultActions = mockMvc.perform(get("/api/member/username/{username}", username).accept(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(Message.USERNAME_DUPLICATED.toString())));
	}
	/**
	 * 적절하지 않은 형식의 아이디 입력으로 중복체크 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckUsernameFailureBecauseInvalidData() throws Exception {
		String username = "use";

		ResultActions resultActions = mockMvc.perform(get("/api/member/username/{username}", username).accept(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isNotFound()).andDo(print());
	}
	
	/**
	 * 로그인 성공 - 아이디와 비밀번호에 맞는 회원이 있음
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testLoginSuccessWithMatchingAccount() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("user", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.LOGIN_SUCCESS.toString())));
	}
	/**
	 * 로그인 실패 - 아이디와 비밀번호에 맞는 회원이 없음
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testLoginSuccessWithNotMatchingAccount() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("testuser", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(Message.LOGIN_FAILURE.toString())));
	}
	/**
	 * 적절하지 않은 데이터 입력으로 로그인 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testLoginFailureBecauseInvalidData() throws Exception {
		MemberLoginVo memberVo = new MemberLoginVo("user**", "asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}
