package com.cafe24.shoppingmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.dto.BucketItem;
import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.vo.BucketItemVo;

@Service
public class BucketService {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	// 장바구니 등록
	public Boolean registBucket(Map<String, Object> bucketMap) {
		String endpoint = "http://localhost:8888/api/buckets";
		JSONResultBoolean result = restTemplate.postForObject(endpoint, bucketMap, JSONResultBoolean.class); 
		
		return result.getData();
	}
	// 장바구니 조회
	public List<BucketItem> getMemberBucketList(Long memberNo) {
		String endpoint = "http://localhost:8888/api/buckets/members/" + memberNo;
		JSONResultBucketList result = restTemplate.getForObject(endpoint, JSONResultBucketList.class);
		
		return result.getData();
	}
	public List<BucketItem> getNonMemberBucketList(String identifier) {
		String endpoint = "http://localhost:8888/api/buckets/non-members/" + identifier;
		JSONResultBucketList result = restTemplate.getForObject(endpoint, JSONResultBucketList.class);
		
		return result.getData();
	}
	// 장바구니에 담긴 물품들의 총 금액 조회
	public Integer getBucketTotalPrice(Long memberNo, String identifier) {
		String endpoint = "http://localhost:8888/api/buckets/price?";
		if(memberNo != null) {
			endpoint += "memberNo=" + memberNo;
		} else {
			endpoint += "identifier=" + identifier;
		}
		JSONResultInteger result = restTemplate.getForObject(endpoint, JSONResultInteger.class);

		return result.getData();
	}
	// 장바구니 수량 수정
	public Boolean modifyBucket(BucketItemVo bucketItemVo) {
		String endpoint = "http://localhost:8888/api/buckets";
		
		HttpEntity<BucketItemVo> requestEntity = new HttpEntity<>(bucketItemVo);
		ResponseEntity<JSONResultBoolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, requestEntity, JSONResultBoolean.class);
		
		return responseEntity.getBody().getData();
	}
	// 장바구니 삭제
	public Boolean deleteBucketList(List<BucketItemVo> bucketList) {
		String endpoint = "http://localhost:8888/api/buckets";
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(bucketList);
		ResponseEntity<JSONResultBoolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.DELETE, requestEntity, JSONResultBoolean.class);
		
		return responseEntity.getBody().getData();
	}
	
	private static class JSONResultBoolean extends JSONResult<Boolean> {
	}
	private static class JSONResultBucketList extends JSONResult<List<BucketItem>> {
	}
	private static class JSONResultInteger extends JSONResult<Integer> {
	}
}
