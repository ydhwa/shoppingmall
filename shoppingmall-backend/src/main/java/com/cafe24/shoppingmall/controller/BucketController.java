package com.cafe24.shoppingmall.controller;

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

/**
 * 장바구니에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("BucketAPIController")
@RequestMapping("/buckets")
public class BucketController {

//	@Autowired
//	private BucketService bucketService;
	
	// 장바구니 담기
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> putAnItemInBucket() {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 회원의 장바구니 조회
	@RequestMapping(value="/members/{memberno}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMemberBuckets(@PathVariable("memberno") Optional<Long> memberNo) {
		// path variable check
		if(!memberNo.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 비회원의 장바구니 조회
	@RequestMapping(value="/non-members/{identifier}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showNonMemberBuckets(@PathVariable("identifier") Optional<String> identifier) {
		// path variable check
		if(!identifier.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 수량 수정
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyQuantityOfBucketItem(@PathVariable("no") Optional<Long> no, @RequestParam(value="quantity", required=true) Integer quantity) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> method(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
}
