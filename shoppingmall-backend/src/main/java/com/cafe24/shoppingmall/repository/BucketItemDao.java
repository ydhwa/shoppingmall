package com.cafe24.shoppingmall.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.BucketItemVo;

@Repository
public class BucketItemDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public Boolean insertBucketItems(Map<String, Object> bucketMap) {
		return 1 == sqlSession.insert("bucketitem.insertItems", bucketMap);
	}

	// 주문 후 장바구니에 담겨 있던 물품들을 없애야 함
	public Boolean deleteItems(List<BucketItemVo> ordersItemList) {
		return 1 == sqlSession.delete("bucketitem.deleteItems", ordersItemList);
	}

	public Boolean deleteItemsByNo(List<Long> bucketNoList) {
		return 1 == sqlSession.delete("bucketitem.deleteItemsByNoList", bucketNoList);
	}

	public boolean deleteItemsByProductNo(Long no) {
		return 0 <= sqlSession.delete("bucketitem.deleteItemsByProductNo", no);
	}
	
}
