package com.cafe24.shoppingmall.controller;

import static org.hamcrest.Matchers.is;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
	
	
	private static final String DEFAULT_PATH = "/members";
	/**
	 * 성공 동작 테스트
	 * 
	 * @param method 요청방식
	 * @param url
	 * @param requestData 요청시 데이터
	 * @param expectDataOffset 기대하는 데이터의 위치값 ("$data.username")
	 * @param responseData 응답을 기대하는 데이터
	 * @throws Exception
	 */
	private void successAction(String method, String url, Object requestData, String expectDataOffset, Object responseData) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(DEFAULT_PATH + url);	// default: get

		if("post".contentEquals(method)) {
			requestBuilder = MockMvcRequestBuilders.post(DEFAULT_PATH + url);
		} else if("put".contentEquals("method")) {
			requestBuilder = MockMvcRequestBuilders.put(DEFAULT_PATH + url);
		} else if("delete".contentEquals("method")) {
			requestBuilder = MockMvcRequestBuilders.delete(DEFAULT_PATH + url);
		}
		
		ResultActions resultActions = mockMvc.perform(requestBuilder.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(requestData)));
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success"))).andExpect(jsonPath("$.data" + expectDataOffset, is(responseData)));
	}	
	/**
	 * 실패 동작 테스트
	 * 
	 * @param method 요청방식
	 * @param url
	 * @param requestData 요청시 데이터
	 * @throws Exception
	 */
	private void failureAction(String method, String url, Object requestData) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(DEFAULT_PATH + url);	// default: get

		if("post".contentEquals(method)) {
			requestBuilder = MockMvcRequestBuilders.post(DEFAULT_PATH + url);
		} else if("put".contentEquals("method")) {
			requestBuilder = MockMvcRequestBuilders.put(DEFAULT_PATH + url);
		} else if("delete".contentEquals("method")) {
			requestBuilder = MockMvcRequestBuilders.delete(DEFAULT_PATH + url);
		}
		
		ResultActions resultActions = mockMvc.perform(requestBuilder.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(requestData)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());		
	}
	

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
		
		successAction("post", "", memberVo, "", true);
	}
	/**
	 * 중복된 아이디로 회원가입을 시도하여 회원가입 실패
	 * 
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testJoinSuccessInsertFailure() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf1234!");
		memberVo.setName("회원B");
		memberVo.setBirthDate("1990-01-01");
		memberVo.setHomeNumber("333-333-3333");
		memberVo.setPhoneNumber("333-3333-3333");
		memberVo.setEmail("userB01@test.com");
		
		failureAction("post", "", memberVo);
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
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userB01");
		memberVo.setPassword("asdf1234!");
		memberVo.setBirthDate("1990-01-01");
		memberVo.setHomeNumber("333-333-3333");
		memberVo.setPhoneNumber("333-3333-3333");
		memberVo.setEmail("userB01@test.com");
		
		failureAction("post", "", memberVo);
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
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userB01");
		memberVo.setPassword("asdf1!");
		memberVo.setName("회원B");
		memberVo.setBirthDate("1990-01-01");
		memberVo.setHomeNumber("333-333-3333");
		memberVo.setPhoneNumber("333-3333-3333");
		memberVo.setEmail("userB01@test.com");

		failureAction("post", "", memberVo);
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
		
		successAction("get", "/duplicate?username=" + username, null, "", false);
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

		successAction("get", "/duplicate?username=" + username, null, "", true);
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
		
		failureAction("get", "/duplicate?username=" + username, null);
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
		
		successAction("post", "/login", memberVo, ".username", memberVo.getUsername());
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
		
		successAction("post", "/login", memberVo, "", null);
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
		
		failureAction("post", "/login", memberVo);
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

		failureAction("post", "/login", memberVo);
	}
}
