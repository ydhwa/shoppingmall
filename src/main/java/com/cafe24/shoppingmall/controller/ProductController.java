package com.cafe24.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private ProductService adminProductService;

	/**
	 * 상품을 등록한다.
	 * 
	 * @param productVo 등록할 상품
	 * @return 등록된 상품정보
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@RequestBody ProductVo productVo) {
		ProductVo registProductVo = adminProductService.registerProduct(productVo);
		
		if(productVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 등록에 실패했습니다."));
		}
		else {	// 상품등록 실패
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registProductVo));
		}
	}
}
