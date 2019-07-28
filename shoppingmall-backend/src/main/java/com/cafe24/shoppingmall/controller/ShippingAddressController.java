package com.cafe24.shoppingmall.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.vo.ShippingAddressVo;

/**
 * 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("shippingAddressAPIController")
@RequestMapping("/shipping-addresses")
public class ShippingAddressController {

	@Autowired
	private MemberService memberService;
	
	// 배송지 등록
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> registShippingAddress(@RequestBody ShippingAddressVo shippingAddressVo) {
		if(shippingAddressVo == null) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 특정 회원의 배송지 목록 조회
	@RequestMapping(value="/members/{memberno}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showShippingAddressesOfMember(@PathVariable("memberno") Optional<Long> memberNo) {
		// path variable check
		if(!memberNo.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 배송지 조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showShippingAddress(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 배송지 수정
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyShippingAddress(@RequestBody ShippingAddressVo shippingAddressVo) {
		if(shippingAddressVo == null) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 배송지 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteShippingAddress(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
}
