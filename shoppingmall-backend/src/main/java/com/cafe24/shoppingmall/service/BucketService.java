package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.BucketItemDto;
import com.cafe24.shoppingmall.repository.BucketItemDao;
import com.cafe24.shoppingmall.vo.BucketItemVo;

/**
 * 장바구니에 대한 로직이 담긴 서비스.
 * 
 * @author YDH
 *
 */
@Service
public class BucketService {
	
	@Autowired
	private BucketItemDao bucketItemDao;

	// 장바구니에 상품 품목 추가
	@Transactional
	public Boolean registBucketItems(Long memberNo, String identifier, List<BucketItemVo> bucketItemList, Boolean all) {
		Map<String, Object> bucketMap = new HashMap<String, Object>();
		// 식별자
		bucketMap.put("memberNo", memberNo);
		bucketMap.put("identifier", identifier);
		
		bucketMap.put("bucketItemList", bucketItemList);
		
		// all: 중복된 물품 있을 때 기존 수량에서 주어진 수량이 더해짐
		// all을 보내지 않는다면, 주어진 수량으로 대체한다.
		bucketMap.put("all", all);
		
		return bucketItemDao.insertBucketItems(bucketMap);
	}

	// 장바구니에 담긴 물품 삭제
	public Boolean deleteItems(List<BucketItemVo> bucketNoList) {
		return bucketItemDao.deleteItemsByNo(bucketNoList);
	}

	// 장바구니에 담긴 물품들 보기
	public List<BucketItemDto> showBucketItemList(Long memberNo, String identifier) {
		return bucketItemDao.getList(memberNo, identifier);
	}

	// 장바구니에 담긴 물품의 수량 변경
	public Boolean modifyQuantity(BucketItemVo bucketItemVo) {
		return bucketItemDao.updateQuantity(bucketItemVo);
	}
}
