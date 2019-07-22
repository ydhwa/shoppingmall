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

import com.cafe24.shoppingmall.vo.Enum.ProductDisplayStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.google.gson.Gson;

/**
 * 상품 컨트롤러 동작에 대한 테스트
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
	
	
	private static final String DEFAULT_PATH = "/products";
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
	
	
	// 상품 추가 성공
	@Test
	public void testRegistProductSuccess() throws Exception {
		ProductVo productVo = new ProductVo();
		productVo.setName("상품4");
		productVo.setSupplyPrice(3000);
		productVo.setSellPrice(5000);
		productVo.setSummaryDescription("상품 4입니다.(요약)");
		productVo.setDetailedDescription("상품 4입니다. 세부설명이에요!");
		productVo.setProductDisplayStatus(ProductDisplayStatus.MAIN);
		productVo.setProductManageStatus(ProductManageStatus.STOCK);
		productVo.setStockQuantity(4444);
		productVo.setOptionAvailable(true);
		
		successAction("post", "", productVo, "", true);
	}
	
	// 상품들 검색결과 조회 성공
	@Test
	public void testSearchAndPaginationProductsSuccess() throws Exception {
		successAction("get", "?field=name&keyword=상품&offset=1&limit=10", null, ".length()", 3);
	}
	
	// 상품 상세조회 성공
	@Test
	public void testShowProductDetailsSuccess() throws Exception {
		successAction("get", "/" + 1L, null, ".name", "상품1");
	}
	
	// 상품 수정 성공
	@Test
	public void testModifyProductSuccess() throws Exception {
		ProductVo productVo = new ProductVo();
		productVo.setNo(2L);
		productVo.setName("수정된 상품2");
		productVo.setSellPrice(4000);
		productVo.setProductDisplayStatus(ProductDisplayStatus.MAIN);
		productVo.setStockQuantity(2222);
		productVo.setOptionAvailable(true);
		
		successAction("post", "", productVo, "", true);
	}
	
	// 상품 삭제 성공
	@Test
	public void testDeleteProductSuccess() throws Exception {
		successAction("delete", "/" + 3L, null, "", true);
	}
}
