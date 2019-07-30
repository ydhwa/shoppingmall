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

import com.google.gson.Gson;

/**
 * 카테고리 컨트롤러 테스트
 * 
 * @author YDH
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class CategoryControllerTest {
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
	
	
	private static final String DEFAULT_PATH = "/api/categories";
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
	public void 계층적으로_카테고리_전부_조회_성공() throws Exception {
		successAction("get", "", null, ".length()", 5);
	}
	@Test
	public void 최상위_카테고리들만_조회_성공() throws Exception {
		successAction("get", "/parents", null, ".length()", 2);
	}
	@Test
	public void 하위_카테고리들_포함하여_조회_성공() throws Exception {
		successAction("get", "/" + 1 + "/children", null, ".length()", 4);
	}
	@Test
	public void 특정_카테고리_조회_성공() throws Exception {
		successAction("get", "/" + 1, null, ".name", "카테고리1");
	}
}
