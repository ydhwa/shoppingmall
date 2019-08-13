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

/**
 * 주문에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class OrdersDao {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 주문 등록
	public Boolean insert(OrdersVo orders) {
		return 1 == sqlSession.insert("orders.insert", orders);
	}
	// 주문 물품 등록
	public Boolean insertItems(List<BucketItemVo> ordersItemList) {
		int result = 0;
		for(BucketItemVo ordersItemVo: ordersItemList) {
			result += sqlSession.insert("orders.insertItem", ordersItemVo);
		}
		return ordersItemList.size() == result;
	}
	// 각 물품의 재고 수량을 없애고, 총 결제 금액 업데이트
	public Boolean updateSomeDataAfterOrder(List<BucketItemVo> ordersItemList) {
		if(1 > sqlSession.update("orders.updateTotalOrderAccount")) {
			return false;
		}
		int result = 0;
		for(BucketItemVo bucketItemVo: ordersItemList) {
			result += sqlSession.update("orders.updateStockQuantity", bucketItemVo);
		}
		return ordersItemList.size() ==  result;
	}

	// 특정 회원의 주문 목록 조회
	public List<OrdersSummaryDto> getListByMemberNo(Map<String, Object> ordersMap) {
		return sqlSession.selectList("orders.getByMemberNo", ordersMap);
	}

	// 주문 상세 조회
	public OrdersDetailsDto get(Map<String, Object> ordersMap) {
		return sqlSession.selectOne("orders.get", ordersMap);
	}
	// 주문에 대한 주문 물품 리스트 조회
	public List<OrdersItemDto> getItemListByOrderNo(Long no) {
		return sqlSession.selectList("orders.getItems", no);
	}

	// 주문 취소
	public Boolean modifyStatusToCANCEL(Long no) {
		return 1 == sqlSession.update("orders.updateStatusToCANCEL", no);
	}

	// 관리자가 주문 정보 검색
	public List<OrdersSummaryDto> getListBySearchToAdmin(HashMap<String, String> paramMap) {
		return sqlSession.selectList("orders.searchToAdmin", paramMap);
	}

	// 관리자가 주문 번호로 조회 (더 많은 정보를 열람할 수 있다.)
	public OrdersDetailsDto getByNoToAdmin(Long no) {
		return sqlSession.selectOne("orders.getToAdmin", no);
	}

	// 관리자가 주문 정보 수정(고객이 변경할 수 없는 정보까지 변경할 수 있다.)
	public Boolean modifyStatusToAdmin(OrdersVo ordersVo) {
		return 1 == sqlSession.update("orders.updateStatusToAdmin", ordersVo);
	}

	// 주문에 대한 주문 물품 리스트의 상품 정보 제거(NULL값으로 세팅)
	// 상품 삭제 동작 시 실행된다.
	public boolean updateProductOptionItemNoToNull(Long no) {
		return 0 <= sqlSession.update("orders.updateProductOptionItemNoToNull", no);
	}
	
	// 방금 주문한 주문의 주문 코드를 뽑는다.
	public String getRecentCode() {
		return sqlSession.selectOne("orders.getRecentCode");
	}
}
