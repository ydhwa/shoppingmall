package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.OrdersDetailsDto;
import com.cafe24.shoppingmall.dto.OrdersSummaryDto;
import com.cafe24.shoppingmall.repository.BucketItemDao;
import com.cafe24.shoppingmall.repository.OrdersDao;
import com.cafe24.shoppingmall.vo.BucketItemVo;
import com.cafe24.shoppingmall.vo.OrdersVo;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private BucketItemDao bucketItemDao;

	// 주문을 등록한다.
	// 등록 로직은 다음과 같다.
	/*
	 * (주문 시 주문 대상 물품이 이미 장바구니에 담겨 있다고 가정한다.)
	 * 1. 주문을 등록한다.
	 * 2. 주문 품목에 선택했던 물건들을 추가한다.
	 * 3. 주문했던 물품들의 재고를 주문한 만큼 없앤다.
	 *    총 주문금액을 1의 주문 정보에 반영한다. (주문 화면에서 보여주는 것은 별도의 쿼리를 이용하여 보여줘야 할 것이다.)
	 * 4. 주문한 물품들을 장바구니에서 없앤다. 
	 */
	@Transactional
	public Boolean registOrder(OrdersVo orders, List<BucketItemVo> ordersItemList) {
		if(!ordersDao.insert(orders)) {
			return false;
		}
		if(!ordersDao.insertItems(ordersItemList)) {
			return false;
		}
		if(!ordersDao.updateSomeDataAfterOrder(ordersItemList)) {
			return false;
		}
		if(!bucketItemDao.deleteItems(ordersItemList)) {
			return false;
		}
		
		return true;
	}

	public List<OrdersSummaryDto> showOrdersByMemberNo(Long no, String offset, String limit) {
		Map<String, Object> ordersMap = new HashMap<>();
		ordersMap.put("no", no);
		ordersMap.put("offset", offset);
		ordersMap.put("limit", limit);
		
		return ordersDao.getListByMemberNo(ordersMap);
	}

	public OrdersDetailsDto showOrdersDetails(Long no, OrdersVo ordersVo) {
		Map<String, Object> ordersMap = new HashMap<>();
		ordersMap.put("no", no);
		ordersMap.put("orders", ordersVo);
		
		OrdersDetailsDto ordersDto = ordersDao.get(ordersMap);
		ordersDto.setOrdersItemDtoList(ordersDao.getItemListByOrderNo(ordersDto.getNo()));
		
		return ordersDto;
	}

	public Boolean modifyOrderStatusToCANCEL(Long no) {
		return ordersDao.modifyStatusToCANCEL(no);
	}

}
