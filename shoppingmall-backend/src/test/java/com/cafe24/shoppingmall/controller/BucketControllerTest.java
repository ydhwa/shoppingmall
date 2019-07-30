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
import com.google.gson.Gson;

/**
 * 장바구니 컨트롤러 테스트
 * 
 * @author YDH
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BucketControllerTest {
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
	
	
	private static final String DEFAULT_PATH = "/api/buckets";
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
	public void 회원이_장바구니_등록_성공() throws Exception {
		Map<String, Object> bucketMap = new HashMap<>();
		
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(1L, 1));
		
		bucketMap.put("memberNo", 2L);
		bucketMap.put("bucketItemList", bucketItemList);

		successAction("post", "", bucketMap, "", true);
	}
	// 식별자를 발급하는 건 프론트엔드 단에서 해야 할 것 같아 제외
	/*@Test
	public void 비회원이_장바구니_식별자를_발급받으며_장바구니_등록_성공() throws Exception {
		Map<String, Object> bucketMap = new HashMap<>();
		
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(1L, 2));
		
		bucketMap.put("bucketItemList", bucketItemList);
		
		successAction("post", "", bucketMap, "", true);
	}*/
	@Test
	public void 장바구니에_넣은_이력이_있던_비회원이_장바구니_등록_성공() throws Exception {
		Map<String, Object> bucketMap = new HashMap<>();
		
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(1L, 2));
		
		bucketMap.put("identifier", "19072520022353484b4fc3b-115a-43b4-a57b-fdd9e48aa2ef");
		bucketMap.put("bucketItemList", bucketItemList);
		
		successAction("post", "", bucketMap, "", true);
	}
	@Test
	public void 기존_장바구니에_들어있던_품목_대체하지_않고_수량을_더하면서_등록_성공() throws Exception {
		Map<String, Object> bucketMap = new HashMap<>();
		
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(1L, 1));	// 겹치는 품목
		bucketItemList.add(new BucketItemVo(2L, 3));	// 겹치지 않는 품목
		
		bucketMap.put("memberNo", 1L);
		bucketMap.put("bucketItemList", bucketItemList);
		bucketMap.put("all", true);
		
		successAction("post", "", bucketMap, "", true);
	}
	@Test
	public void 기존_장바구니에_들어있던_품목_대체하면서_등록_성공() throws Exception {
		Map<String, Object> bucketMap = new HashMap<>();
		
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(2L, 1));	// 겹치는 품목
		bucketItemList.add(new BucketItemVo(3L, 1));	// 겹치지 않는 품목
		
		bucketMap.put("identifier", "19072520022353484b4fc3b-115a-43b4-a57b-fdd9e48aa2ef");
		bucketMap.put("bucketItemList", bucketItemList);
		
		successAction("post", "", bucketMap, "", true);
	}
	
	@Test
	public void 회원이_장바구니_조회_성공() throws Exception {
		successAction("get", "/members/1", null, ".length()", 2);
	}
	@Test
	public void 비회원이_장바구니_조회_성공() throws Exception {
		successAction("get", "/non-members/19072520022353484b4fc3b-115a-43b4-a57b-fdd9e48aa2ef", null, ".length()", 1);
	}
	
	@Test
	public void 장바구니의_수량_변경_성공() throws Exception {
		BucketItemVo bucketItemVo = new BucketItemVo();
		bucketItemVo.setMemberNo(1L);
		bucketItemVo.setProductOptionItemNo(1L);
		bucketItemVo.setQuantity(2);
		
		successAction("put", "", bucketItemVo, "", true);
	}
	
	@Test
	public void 회원이_장바구니에서_물품들_선택하여_삭제_성공() throws Exception {
		List<Long> bucketItemNoList = new ArrayList<>();
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList.add(new BucketItemVo(1L, 1L));
		bucketItemList.add(new BucketItemVo(1L, 2L));
		
		successAction("delete", "", bucketItemNoList, "", true);
	}
	
}
