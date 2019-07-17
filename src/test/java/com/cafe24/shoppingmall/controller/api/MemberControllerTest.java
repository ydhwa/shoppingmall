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

/**
 * 회원 컨트롤러 동작에 대한 테스트
 * 
 * @author YDH
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MemberControllerTest {
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

	/**
	 * 회원가입 성공 - 회원 테이블에 정보를 넣는 것까지 성공
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
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
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
	}
	/**
	 * 중복된 아이디로 회원가입을 시도하여 회원가입 실패
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testJoinSuccessInsertFailure() throws Exception {
		MemberVo memberVo = new MemberVo("userA01", "asdf1234!", "회원B", "1990-01-01", "333-333-3333", "333-3333-3333", "userB01@test.com");

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
		MemberVo memberVo = new MemberVo("userB01", "asdf1234!", null, "1990-01-01", "333-333-3333", "333-3333-3333", "userB01@test.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());

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
		MemberVo memberVo = new MemberVo("userB01", "asdf1!", "회원B-1", "1990-01-01", null, "333-3333-3333", "userB01@test.com");

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
//	@Ignore
	public void testCheckUsernameDuplicationSuccessWithNotDuplicatedUsername() throws Exception {
		String username = "userB02";

		ResultActions resultActions = mockMvc.perform(get("/api/member/username/{username}", username).accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(false)));
	}
	/**
	 * 아이디 중복체크 - 중복되는 아이디가 있음
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testCheckUsernameDuplicationSuccessWithDuplicatedUsername() throws Exception {
		String username = "userA01";

		ResultActions resultActions = mockMvc.perform(get("/api/member/username/{username}", username).accept(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
	}
	/**
	 * 적절하지 않은 형식의 아이디 입력으로 중복체크 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testCheckUsernameDuplicationFailureBecauseInvalidData() throws Exception {
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
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.username", is(memberVo.getUsername())));
	}
	/**
	 * 로그인 실패 - 아이디와 비밀번호에 맞는 회원이 없음
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testLoginSuccessWithNotMatchingAccount() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf1234@");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data").doesNotExist());
	}
	/**
	 * 아이디나 비밀번호를 입력하지 않아 로그인 실패
	 * (validation)
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testLoginFailureBecauseMissingData() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("");
		memberVo.setPassword("asdf1234!");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
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
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}
