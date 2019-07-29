package com.cafe24.shoppingmall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersItemDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
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

	public List<OrdersSummaryDto> getListByMemberNo(Map<String, Object> ordersMap) {
		return sqlSession.selectList("orders.getByMemberNo", ordersMap);
	}

	public OrdersDetailsDto get(Map<String, Object> ordersMap) {
		return sqlSession.selectOne("orders.get", ordersMap);
	}
	public List<OrdersItemDto> getItemListByOrderNo(Long no) {
		return sqlSession.selectList("orders.getItems", no);
	}

	public Boolean modifyStatusToCANCEL(Long no) {
		return 1 == sqlSession.update("orders.updateStatusToCANCEL", no);
	}

	public List<OrdersSummaryDto> getListBySearchToAdmin(HashMap<String, String> paramMap) {
		return sqlSession.selectList("orders.searchToAdmin", paramMap);
	}

	public OrdersDetailsDto getByNoToAdmin(Long no) {
		return sqlSession.selectOne("orders.getToAdmin", no);
	}

	public Boolean modifyStatusToAdmin(OrdersVo ordersVo) {
		return 1 == sqlSession.update("orders.updateStatusToAdmin", ordersVo);
	}

	public boolean updateProductOptionItemNoToNull(Long no) {
		return 0 <= sqlSession.update("orders.updateProductOptionItemNoToNull", no);
	}
	
	
	
}
