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
import com.cafe24.shoppingmall.service.BucketService;
import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 장바구니에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("bucketAPIController")
@RequestMapping("/buckets")
public class BucketController {

	@Autowired
	private BucketService bucketService;
	
	// 장바구니 담기
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> putItemsInBucket(@RequestBody Map<String, Object> bucketMap) {
		// all이 null이 아니라면 담을 때 동일한 품목이 있다면 기존 수량과 합침
		if(bucketMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("장바구니 추가에 실패했습니다."));
		}
		
		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		Long memberNo = mapper.convertValue(bucketMap.get("memberNo"), Long.class);
		String identifier = mapper.convertValue(bucketMap.get("identifier"), String.class);
		List<BucketItemVo> bucketItemList = new ArrayList<>();
		bucketItemList = mapper.convertValue(bucketMap.get("bucketItemList"), new TypeReference<List<BucketItemVo>>() {});
		Boolean all = mapper.convertValue(bucketMap.get("all"), Boolean.class);
		
		// 필수 사항들에 대하여 체크한다.
		if((memberNo == null && identifier == null) || bucketItemList == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("장바구니 추가에 실패했습니다."));
		}
		
		// 다시 map에 넣는다. - 한 번에 처리하기 위함
		Map<String, Object> newBucketMap = new HashMap<>();
		newBucketMap.put("memberNo", memberNo);
		newBucketMap.put("identifier", identifier);
		newBucketMap.put("bucketItemList", bucketItemList);
		newBucketMap.put("all", all);
		
		Boolean registResult = bucketService.registBucketItems(memberNo, identifier, bucketItemList, all);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
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
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> method(@RequestBody List<Long> bucketNoList) {
		// path variable check
		if(bucketNoList == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("장바구니 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = bucketService.deleteItems(bucketNoList);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
	
}
