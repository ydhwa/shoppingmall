package com.cafe24.shoppingmall.controller;

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
import com.cafe24.shoppingmall.vo.ProductVo;

/**
 * 상품에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("ProductAPIController")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 상품을 등록한다.
	 * 
	 * @param productVo 등록할 상품
	 * @return 등록된 상품정보
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> registProduct(@RequestBody ProductVo productVo) {
		ProductVo registProductVo = productService.registerProduct(productVo);
		
		if(productVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 등록에 실패했습니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registProductVo));
		}
	}
	
	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(
			@RequestParam(value="field", defaultValue="") String field, 
			@RequestParam(value="keyword", defaultValue="") String keyword, 
			@RequestParam(value="offset", required=true) Integer offset, 
			@RequestParam(value="limit", required=true) Integer limit) {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 수정
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyProduct(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteProduct(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
}
