package com.cafe24.shoppingmall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 상품에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("adminProductAPIController")
@RequestMapping("/admin/products")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 상품을 등록한다.
	 * 
	 * @param productVo 등록할 상품
	 * @return 등록된 상품정보
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> registProduct(@RequestBody Map<String, Object> productMap) {
		if (productMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}

		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		ProductVo product = mapper.convertValue(productMap.get("product"), ProductVo.class);
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		List<CategoryVo> categoryList = mapper.convertValue(productMap.get("categoryList"), new TypeReference<List<CategoryVo>>() {});
		List<ProductImageVo> productImageList = mapper.convertValue(productMap.get("productImageList"), new TypeReference<List<ProductImageVo>>() {});
		
		// 필수 사항들(상품, 옵션, 품목)에 대하여 체크한다.
		if(product == null || productOptionList == null || productOptionItemList == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}
		
		if("N".equals(product.getOptionAvailable())) {	// 옵션을 사용하지 않는 경우
			productOptionList.add(new ProductOptionVo("없음", null));
			productOptionItemList.add(new ProductOptionItemVo(null, null, "없음", 0, product.getAvailability(), product.getManageStatus(), product.getStockQuantity()));
		} else {	// 옵션을 사용하는 경우
			productOptionList = mapper.convertValue(productMap.get("productOptionList"), new TypeReference<List<ProductOptionVo>>() {});
			productOptionItemList = mapper.convertValue(productMap.get("productOptionItemList"), new TypeReference<List<ProductOptionItemVo>>() {});
		}
		
		Boolean registResult = productService.registProduct(product, productOptionList, productOptionItemList, categoryList, productImageList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 검색에 실패했습니다."));
		}

		List<ProductSummaryDto> productList = productService.searchProductsToAdmin(paramMap);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
	}

	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 상세 조회에 실패했습니다."));
		}

		ProductDetailsDto product = productService.showProductToAdmin(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product));
	}

	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyProduct(@RequestBody Map<String, Object> productMap) {
		if (productMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}

		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		ProductVo product = mapper.convertValue(productMap.get("product"), ProductVo.class);
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		List<CategoryVo> categoryList = mapper.convertValue(productMap.get("categoryList"), new TypeReference<List<CategoryVo>>() {});
		List<ProductImageVo> productImageList = mapper.convertValue(productMap.get("productImageList"), new TypeReference<List<ProductImageVo>>() {});
		
		// 필수 사항들(상품, 옵션, 품목)에 대하여 체크한다.
		if(product == null || productOptionList == null || productOptionItemList == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}
		
		if("N".equals(product.getOptionAvailable())) {	// 옵션을 사용하지 않는 경우
			productOptionList.add(new ProductOptionVo("없음", null));
			productOptionItemList.add(new ProductOptionItemVo(null, null, "없음", 0, product.getAvailability(), product.getManageStatus(), product.getStockQuantity()));
		} else {	// 옵션을 사용하는 경우
			productOptionList = mapper.convertValue(productMap.get("productOptionList"), new TypeReference<List<ProductOptionVo>>() {});
			productOptionItemList = mapper.convertValue(productMap.get("productOptionItemList"), new TypeReference<List<ProductOptionItemVo>>() {});
		}
		
		Boolean registResult = productService.modifyProductToAdmin(product, productOptionList, productOptionItemList, categoryList, productImageList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteProduct(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = productService.deleteProductToAdmin(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
}
