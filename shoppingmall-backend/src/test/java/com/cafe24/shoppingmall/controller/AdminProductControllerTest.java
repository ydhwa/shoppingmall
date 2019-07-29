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

import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.Enum.ProductDisplayStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductImageStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionValueVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
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
public class AdminProductControllerTest {
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
	
	
	private static final String DEFAULT_PATH = "/admin/products";
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
	public void 관리자가_옵션_있는_상품_추가_성공() throws Exception {
		Map<String, Object> productMap = new HashMap<>();
		
		ProductVo productVo = new ProductVo();
		productVo.setName("상품5");
		productVo.setSupplyPrice(5000);
		productVo.setSellPrice(50000);
		productVo.setSummaryDescription("요약설명-5");
		productVo.setDetailedDescription("상세설명-5");
		productVo.setWeight(5.55);
		productVo.setOptionAvailable("Y");
		productVo.setProductDisplayStatus(ProductDisplayStatus.MAIN);
		productVo.setAvailability("Y");
		productVo.setProductManageStatus(ProductManageStatus.STOCK);
		productVo.setStockQuantity(500);
		
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionValueVo> productOptionValueList1 = new ArrayList<>();
		productOptionValueList1.add(new ProductOptionValueVo("L"));
		productOptionValueList1.add(new ProductOptionValueVo("XL"));
		List<ProductOptionValueVo> productOptionValueList2 = new ArrayList<>();
		productOptionValueList2.add(new ProductOptionValueVo("WHITE"));
		productOptionList.add(new ProductOptionVo("사이즈", productOptionValueList1));
		productOptionList.add(new ProductOptionVo("색상", productOptionValueList2));

		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		productOptionItemList.add(new ProductOptionItemVo("1;2", "1;1", "사이즈=L;색상=WHITE", 1000, "Y", ProductManageStatus.STOCK, 500));
		productOptionItemList.add(new ProductOptionItemVo("1;2", "2;1", "사이즈=XL;색상=WHITE", 1500, "Y", ProductManageStatus.STOCK, 500));

		List<CategoryVo> categoryList = new ArrayList<>();
		categoryList.add(new CategoryVo(1L));
		categoryList.add(new CategoryVo(2L));
		categoryList.add(new CategoryVo(3L));
		
		List<ProductImageVo> productImageList = new ArrayList<>();
		productImageList.add(new ProductImageVo("20190724202712345", "jpg", "/images/", ProductImageStatus.SUB));
		productImageList.add(new ProductImageVo("20190730111111111", "png", "/images/", ProductImageStatus.MAIN));
		
		productMap.put("product", productVo);
		productMap.put("productOptionList", productOptionList);
		productMap.put("productOptionItemList", productOptionItemList);
		productMap.put("categoryList", categoryList);
		productMap.put("productImageList", productImageList);

		successAction("post", "", productMap, "", true);
	}
	@Test
	public void 관리자가_옵션_없는_상품_추가_성공() throws Exception {
		Map<String, Object> productMap = new HashMap<>();
		
		ProductVo productVo = new ProductVo();
		productVo.setName("상품6");
		productVo.setSupplyPrice(6000);
		productVo.setSellPrice(60000);
		productVo.setSummaryDescription("요약설명-6");
		productVo.setDetailedDescription("상세설명-6");
		productVo.setWeight(6.0);
		productVo.setOptionAvailable("N");
		productVo.setProductDisplayStatus(ProductDisplayStatus.NONE);
		productVo.setAvailability("Y");
		productVo.setProductManageStatus(ProductManageStatus.STOCK);
		productVo.setStockQuantity(600);
		
		productMap.put("product", productVo);
		
		successAction("post", "", productMap, "", true);
	}
	
	@Test
	public void 관리자가_상품목록_검색결과_조회_성공() throws Exception {
		successAction("get", "?name=상품&code=P0000004&offset=0&limit=10", null, ".length()", 1);
	}
	
	@Test
	public void 관리자가_상품_상세조회_성공() throws Exception {
		successAction("get", "/" + 1L, null, ".name", "상품1");
	}
	
	@Test
	public void 관리자가_상품_수정_성공() throws Exception {
		Map<String, Object> productMap = new HashMap<>();
		
		ProductVo productVo = new ProductVo();
		productVo.setNo(1L);
		productVo.setName("수정된상품1");
		productVo.setSupplyPrice(1111);
		productVo.setSellPrice(11111);
		productVo.setSummaryDescription("요약설명-1");
		productVo.setDetailedDescription("상세설명-1");
		productVo.setWeight(1.1);
		productVo.setOptionAvailable("Y");
		productVo.setProductDisplayStatus(ProductDisplayStatus.MAIN);
		productVo.setAvailability("Y");
		productVo.setProductManageStatus(ProductManageStatus.STOCK);
		productVo.setStockQuantity(300);
		
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionValueVo> productOptionValueList1 = new ArrayList<>();
		productOptionValueList1.add(new ProductOptionValueVo("L"));
		productOptionValueList1.add(new ProductOptionValueVo("XL"));
		List<ProductOptionValueVo> productOptionValueList2 = new ArrayList<>();
		productOptionValueList2.add(new ProductOptionValueVo("WHITE"));
		productOptionList.add(new ProductOptionVo("사이즈", productOptionValueList1, productVo.getNo()));
		productOptionList.add(new ProductOptionVo("색상", productOptionValueList2, productVo.getNo()));

		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		productOptionItemList.add(new ProductOptionItemVo(productVo.getNo(), "1;2", "1;1", "사이즈=L;색상=WHITE", 1000, "Y", ProductManageStatus.STOCK, 500));
		productOptionItemList.add(new ProductOptionItemVo(productVo.getNo(), "1;2", "2;1", "사이즈=XL;색상=WHITE", 1500, "Y", ProductManageStatus.STOCK, 500));

		List<CategoryVo> categoryList = new ArrayList<>();
		categoryList.add(new CategoryVo(1L, productVo.getNo()));
		categoryList.add(new CategoryVo(2L, productVo.getNo()));
		categoryList.add(new CategoryVo(3L, productVo.getNo()));
		
		List<ProductImageVo> productImageList = new ArrayList<>();
		productImageList.add(new ProductImageVo(productVo.getNo(), "20190724202712345", "jpg", "/images/", ProductImageStatus.SUB));
		productImageList.add(new ProductImageVo(productVo.getNo(), "20190730111111111", "png", "/images/", ProductImageStatus.MAIN));
		
		productMap.put("product", productVo);
		productMap.put("productOptionList", productOptionList);
		productMap.put("productOptionItemList", productOptionItemList);
		productMap.put("categoryList", categoryList);
		productMap.put("productImageList", productImageList);

		successAction("put", "", productMap, "", true);
	}
	
	@Test
	public void 관리자가_상품_삭제_성공() throws Exception {
		successAction("delete", "/" + 3L, null, "", true);
	}
}
