package com.cafe24.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 * 상품에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("productAPIController")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 검색결과 조회
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 검색에 실패했습니다."));
		}

		List<ProductSummaryDto> productList = productService.searchProducts(paramMap);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
	}

	// 상세조회
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 상세 조회에 실패했습니다."));
		}

		ProductDetailsDto product = productService.showProduct(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product));
	}
}
