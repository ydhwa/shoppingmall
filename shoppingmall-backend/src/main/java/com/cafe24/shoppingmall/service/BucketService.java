package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public Boolean registBucketItems(Long memberNo, String identifier, List<BucketItemVo> bucketItemList, Boolean all) {
		Map<String, Object> bucketMap = new HashMap<String, Object>();
		bucketMap.put("memberNo", memberNo);
		bucketMap.put("identifier", identifier);
		bucketMap.put("bucketItemList", bucketItemList);
		bucketMap.put("all", all);
		
		return bucketItemDao.insertBucketItems(bucketMap);
	}

}
