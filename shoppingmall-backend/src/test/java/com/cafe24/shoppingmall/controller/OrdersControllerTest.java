package com.cafe24.shoppingmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.Enum.OrdersStatus;
import com.cafe24.shoppingmall.vo.OrdersVo;
import com.google.gson.Gson;

/**
 * 주문 컨트롤러 동작에 대한 테스트
 * 
 * @author YDH
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class OrdersControllerTest {
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
	
	
	private static final String DEFAULT_PATH = "/orders";
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
	public void 회원이_주문_성공() throws Exception {
		Map<String, Object> ordersMap = new HashMap<>();
		
		OrdersVo ordersVo = new OrdersVo("안전하게 배송해주세요~^^", OrdersStatus.ORDER_COMPLETE,
					"주문자", "02-000-0000", "010-0000-0000", "orderer@email.com", "00000", "서울특별시 서초구", "테헤란로",
					"수령자", "033-000-0000", "011-0000-0000", "11111", "강원도 춘천시", "효자동",
					"Y", 1L, null);
		List<BucketItemVo> orderItemList = new ArrayList<>();
		orderItemList.add(new BucketItemVo(1L, 3));
		orderItemList.add(new BucketItemVo(2L, 2));
		
		ordersMap.put("orders", ordersVo);
		ordersMap.put("ordersItemList", orderItemList);

		successAction("post", "", ordersMap, "", true);
	}
	@Test
	public void 비회원이_주문_성공() throws Exception {
		Map<String, Object> ordersMap = new HashMap<>();
		
		OrdersVo ordersVo = new OrdersVo("안전하게 배송해주세요~^^", OrdersStatus.ORDER_COMPLETE,
					"주문자", "02-000-0000", "010-0000-0000", "orderer@email.com", "00000", "서울특별시 서초구", "테헤란로",
					"수령자", "033-000-0000", "011-0000-0000", "11111", "강원도 춘천시", "효자동",
					"N", null, "asdf1234!");
		List<BucketItemVo> orderItemList = new ArrayList<>();
		orderItemList.add(new BucketItemVo(1L, 3));
		orderItemList.add(new BucketItemVo(2L, 2));
		
		ordersMap.put("orders", ordersVo);
		ordersMap.put("ordersItemList", orderItemList);

		successAction("post", "", ordersMap, "", true);
	}
	@Test
	public void 구매하는_상품이_없어_주문_실패() throws Exception {
		Map<String, Object> ordersMap = new HashMap<>();
		
		OrdersVo ordersVo = new OrdersVo("안전하게 배송해주세요~^^", OrdersStatus.ORDER_COMPLETE,
					"주문자", "02-000-0000", "010-0000-0000", "orderer@email.com", "00000", "서울특별시 서초구", "테헤란로",
					"수령자", "033-000-0000", "011-0000-0000", "11111", "강원도 춘천시", "효자동",
					"Y", 1L, null);
		
		ordersMap.put("orders", ordersVo);

		failureAction("post", "", ordersMap);
	}
	
	@Test
	public void 특정_회원의_주문_내역_조회_성공() throws Exception {
		successAction("get", "/members/2?offset=0&limit=5", null, ".length()", 2);
	}
	@Test
	public void 회원이_주문_상세조회_성공() throws Exception {
		// 회원번호 확인
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setMemberStatus("Y");
		ordersVo.setMemberNo(2L);
		
		successAction("post", "/1", ordersVo, ".ordererName", "주문1-주문자");
		successAction("post", "/1", ordersVo, ".ordersItemDtoList.length()", 3);
	}
	@Test
	public void 비회원이_주문_상세조회_성공() throws Exception {
		// 비밀번호 확인
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setMemberStatus("N");
		ordersVo.setPassword("asdf1234!");
		
		successAction("post", "/2", ordersVo, ".ordererName", "주문2-주문자");
	}
	
	@Test
	public void 주문상태_취소로_수정_성공() throws Exception {
		successAction("put", "/1", null, "", true);
	}
	
	// 삭제는 일부러 넣지 않음.
	
}
