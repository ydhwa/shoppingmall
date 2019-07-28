package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.OrdersVo;

@Repository
public class OrdersDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public Boolean insert(OrdersVo orders) {
		return 1 == sqlSession.insert("orders.insert", orders);
	}

	public Boolean insertItems(List<BucketItemVo> ordersItemList) {
		int result = 0;
		for(BucketItemVo ordersItemVo: ordersItemList) {
			result += sqlSession.insert("orders.insertItem", ordersItemVo);
		}
		return ordersItemList.size() == result;
	}

	// 각 물품의 재고 수량을 없애고, 총 결제 금액 업데이트
	public Boolean updateSomeDataAfterOrder(List<BucketItemVo> ordersItemList) {
		if(1 > sqlSession.update("updateTotalOrderAccount")) {
			return false;
		}
		int result = 0;
		for(BucketItemVo bucketItemVo: ordersItemList) {
			result += sqlSession.update("orders.updateStockQuantity", bucketItemVo);
		}
		return ordersItemList.size() ==  result;
	}
	
	
}