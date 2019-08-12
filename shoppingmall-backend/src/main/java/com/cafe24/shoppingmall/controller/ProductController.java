package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 상품에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("productAPIController")
@RequestMapping("/api/products")
@Api(value="/api/products", description="상품 컨트롤러", consumes="application/json")
public class ProductController {

	@Autowired
	private ProductService productService;

	// swagger로는 테스트 할 수 없다.
	@ApiOperation(value="상품 검색목록 조회", response=ProductSummaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="paramMap", value="검색조건", dataType="string", paramType="query")
	})	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 검색에 실패했습니다."));
		}

		List<ProductSummaryDto> productList = productService.searchProducts(paramMap);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
	}

	@ApiOperation(value="상품 상세조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 상품 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 상세 조회에 실패했습니다."));
		}

		ProductDetailsDto product = productService.showProduct(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product));
	}
	
	// 품목 조회(장바구니, 주문 등에 필요)
	@ApiOperation(value="품목 조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="productNo", value="해당하는 상품 번호", dataType="string", paramType="query"),
		@ApiImplicitParam(name="optionValueKeys", value="해당하는 품목의 옵션값 인덱스들", dataType="string", paramType="query")
	})
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductItem(@RequestParam HashMap<String, String> paramMap) {
		// path variable check
		if (!paramMap.containsKey("productNo") || !paramMap.containsKey("optionValueKeys")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("품목 조회에 실패했습니다."));
		}

		ProductOptionItemVo productItem = productService.showProductItem(paramMap);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productItem));
	}
}
