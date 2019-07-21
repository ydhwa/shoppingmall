package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

import com.cafe24.shoppingmall.vo.ProductVo;
import com.google.gson.Gson;

/**
 * 관리자의 상품 관리 동작에 대한 테스트
 * 
 * @author YDH
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProductControllerTest {
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
	 * 성공적으로 상품을 등록함
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterProductSuccessful() throws Exception {
		ProductVo productVo = new ProductVo();
		// 

		ResultActions resultActions = mockMvc.perform(
				post("/products").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(notNullValue())));
	}
	
}
