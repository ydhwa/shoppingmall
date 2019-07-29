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
@RestController("productAPIController")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(
			@RequestParam(value="no") Long no,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="categoryno") Long categoryNo,
			@RequestParam(value="displaystatus") String displayStatus,
			@RequestParam(value="offset", required=true) Integer offset,
			@RequestParam(value="limit", required=true) Integer limit) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("code", code);
		map.put("name", name);
		map.put("categoryno", categoryNo);
		map.put("displaystatus", displayStatus);
		map.put("offset", offset);
		map.put("limit", limit);

//		List<ProductVo> productVoList = productService.searchProducts(map);

//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVoList));
		return null;
	}

	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return null;
		}

//		ProductVo productVo = productService.getProduct(no.get());

//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		return null;
	}
}
