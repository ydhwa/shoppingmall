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

/**
 * 주문에 대한 로직이 담긴 서비스
 * 
 * @author YDH
 *
 */
@Service
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private BucketItemDao bucketItemDao;

	// 주문 등록
	@Transactional
	public String registOrder(OrdersVo orders, List<BucketItemVo> ordersItemList) {
		// (주문 시 주문 대상 물품이 이미 장바구니에 담겨 있다고 가정한다.) 프로세스는 다음과 같다.
		
		// 1. 주문을 등록한다.
		if(!ordersDao.insert(orders)) {
			return null;
		}
		// 2. 주문 품목에 선택했던 물건들을 추가한다.
		if(!ordersDao.insertItems(ordersItemList)) {
			return null;
		}
		// 3. 주문했던 물품들의 재고를 주문한 만큼 없앤다.
		// 총 주문금액을 1의 주문 정보에 반영한다. (주문 화면에서 보여주는 것을 별도의 쿼리를 이용하여 보여줘야 할 것이다.)
		if(!ordersDao.updateSomeDataAfterOrder(ordersItemList)) {
			return null;
		}
		// 4. 주문한 물품들을 장바구니에서 없앤다.
		if(!bucketItemDao.deleteItems(ordersItemList)) {
			return null;
		}
		
		// 5. 방금 주문한 것의 주문번호를 뽑는다.
		String code = ordersDao.getRecentCode();
		return code;
	}

	// 회원의 주문 목록 조회
	public List<OrdersSummaryDto> showOrdersByMemberNo(Long no, String offset, String limit) {
		Map<String, Object> ordersMap = new HashMap<>();
		ordersMap.put("no", no);
		ordersMap.put("offset", offset);
		ordersMap.put("limit", limit);
		
		return ordersDao.getListByMemberNo(ordersMap);
	}

	// 주문 상세 조회
	@Transactional
	public OrdersDetailsDto showOrdersDetails(Long no, OrdersVo ordersVo) {
		Map<String, Object> ordersMap = new HashMap<>();
		ordersMap.put("no", no);
		ordersMap.put("orders", ordersVo);
		
		OrdersDetailsDto ordersDto = ordersDao.get(ordersMap);
		ordersDto.setOrdersItemDtoList(ordersDao.getItemListByOrderNo(ordersDto.getNo()));
		
		return ordersDto;
	}

	// 주문 취소(고객용). 주문 상태를 '취소'로 변경한다.
	public Boolean modifyOrderStatusToCANCEL(Long no) {
		return ordersDao.modifyStatusToCANCEL(no);
	}

	// 주문 검색 결과 조회(관리자용)
	public List<OrdersSummaryDto> showOrdersBySearchToAdmin(HashMap<String, String> paramMap) {
		return ordersDao.getListBySearchToAdmin(paramMap);
	}

	// 주문 상세 조회(관리자용)
	@Transactional
	public OrdersDetailsDto showOrdersDetailsToAdmin(Long no) {
		OrdersDetailsDto ordersDto = ordersDao.getByNoToAdmin(no);
		ordersDto.setOrdersItemDtoList(ordersDao.getItemListByOrderNo(ordersDto.getNo()));

		return ordersDto;
	}

	// 주문 상태 변경(관리자용)
	public Boolean modifyOrderStatusToAdmin(OrdersVo ordersVo) {
		return ordersDao.modifyStatusToAdmin(ordersVo);
	}
}
