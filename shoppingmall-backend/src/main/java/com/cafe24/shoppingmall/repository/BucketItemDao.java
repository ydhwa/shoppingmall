package com.cafe24.shoppingmall.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.BucketItemDto;
import com.cafe24.shoppingmall.vo.BucketItemVo;

/**
 * 장바구니에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class BucketItemDao {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 장바구니에 추가
	public Boolean insertBucketItems(Map<String, Object> bucketMap) {
		return 1 == sqlSession.insert("bucketitem.insertItems", bucketMap);
	}

	// 장바구니에 담겨 있던 물품 삭제
	// 주문 동작 후에 사용됨
	public Boolean deleteItems(List<BucketItemVo> ordersItemList) {
		return 1 == sqlSession.delete("bucketitem.deleteItems", ordersItemList);
	}

	// 장바구니에 담겨 있던 물품 삭제
	public Boolean deleteItemsByNo(List<BucketItemVo> bucketNoList) {
		return 0 <= sqlSession.delete("bucketitem.deleteItemsByNoList", bucketNoList);
	}

	// 장바구니에 담겨 있던 물품 삭제
	// 상품 삭제 동작 후에 사용됨
	public boolean deleteItemsByProductNo(Long no) {
		return 0 <= sqlSession.delete("bucketitem.deleteItemsByProductNo", no);
	}

	// 장바구니 목록 확인
	public List<BucketItemDto> getList(Long memberNo, String identifier) {
		if(memberNo != null) {
			return sqlSession.selectList("bucketitem.getListByMemberNo", memberNo);
		} else {
			return sqlSession.selectList("bucketitem.getListByIdentifier", identifier);
		}
	}

	// 장바구니 품목 수량 변경
	public Boolean updateQuantity(BucketItemVo bucketItemVo) {
		return 1 == sqlSession.update("bucketitem.updateQuantity", bucketItemVo);
	}
}
