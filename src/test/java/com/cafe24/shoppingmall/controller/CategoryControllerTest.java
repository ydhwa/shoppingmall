package com.cafe24.shoppingmall.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.vo.CategoryVo;
import com.google.gson.Gson;

/**
 * 관리자 카테고리 관리 동작에 대한 테스트
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
	
	/**
	 * 카테고리 성공적으로 추가함
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterCategorySuccessful() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		
		ResultActions resultActions = mockMvc.perform(
				post("/categories").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(notNullValue())));
	}
	
	@Test
	public void testUpdateCategorySuccessful() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		
		ResultActions resultActions = mockMvc.perform(
				put("/categories").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(notNullValue())));
	}
	
	@Test
	public void testDeleteCategorySuccessful() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.delete("/categories")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(notNullValue())));
	
	}
}
