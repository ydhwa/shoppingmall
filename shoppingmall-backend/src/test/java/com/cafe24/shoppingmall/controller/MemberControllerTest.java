package com.cafe24.shoppingmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
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
		} else if("put".contentEquals(method)) {
			requestBuilder = MockMvcRequestBuilders.put(DEFAULT_PATH + url);
		} else if("delete".contentEquals(method)) {
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
	

	@Test
	public void 회원가입_성공() throws Exception {
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
	@Test
	public void 중복된_아이디로_회원가입_실패() throws Exception {
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
	@Test
	public void 필수_입력_사항_누락으로_회원가입_실패() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userB01");
		memberVo.setPassword("asdf1234!");
		memberVo.setBirthDate("1990-01-01");
		memberVo.setHomeNumber("333-333-3333");
		memberVo.setPhoneNumber("333-3333-3333");
		memberVo.setEmail("userB01@test.com");
		
		failureAction("post", "", memberVo);
	}
	@Test
	public void 적절하지_않은_입력으로_회원가입_실패() throws Exception {
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
	
	@Test
	public void 중복되지_않는_아이디로_아이디_중복체크() throws Exception {
		String username = "userB02";
		
		successAction("get", "/duplicate?username=" + username, null, "", false);
	}
	@Test
	public void 중복되는_아이디로_아이디_중복체크() throws Exception {
		String username = "userA01";

		successAction("get", "/duplicate?username=" + username, null, "", true);
	}
	@Test
	public void 적절하지_않은_입력으로_아이디_중복체크_실패() throws Exception {
		String username = "use";
		
		failureAction("get", "/duplicate?username=" + username, null);
	}
	
	@Test
	public void 아이디와_비밀번호를_올바르게_입력하여_로그인_성공() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf1234!");
		
		successAction("post", "/login", memberVo, ".username", memberVo.getUsername());
	}
	@Test
	public void 아이디와_비밀번호가_일치하는_회원이_없어_로그인_실패() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf1234@");
		
		successAction("post", "/login", memberVo, "", null);
	}
	@Test
	public void 아이디나_비밀번호를_입력하지_않아_로그인_실패() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("");
		memberVo.setPassword("asdf1234!");
		
		failureAction("post", "/login", memberVo);
	}
	@Test
	public void 적절하지_않은_입력으로_로그인_실패() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUsername("userA01");
		memberVo.setPassword("asdf");

		failureAction("post", "/login", memberVo);
	}
	
	@Test
	public void 회원정보_상세조회_성공() throws Exception {
		successAction("get", "/1", "", ".username", "userA01");
	}
	
	@Test
	public void 회원정보_수정_성공() throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setNo(1L);
		memberVo.setPassword("asdf1234!");
		memberVo.setName("수정된회원A");
		memberVo.setBirthDate("1990-05-05");
		memberVo.setHomeNumber("000-000-0000");
		memberVo.setPhoneNumber("000-0000-0000");
		memberVo.setEmail("userAMod@test.com");
		
		successAction("put", "", memberVo, "", true);
	}
	
	@Test
	public void 회원정보_삭제_성공() throws Exception {
		successAction("delete", "/1", "", "", true);
	}
}
