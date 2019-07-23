package com.cafe24.shoppingmall.vo;

/**
 * Enum 클래스 모음
 * @author YDH
 *
 */
public class Enum {
	
	/**
	 * 고객 상태에 대한 enum 클래스
	 * ENABLE(활성)/DISABLE(비활성)
	 * 
	 * @author YDH
	 *
	 */
	public enum MemberStatus {
		ENABLE,
		DISABLE;

		public String getValue() { return super.toString(); }
	}
	
	
	/**
	 * 고객 권한 역할에 대한 enum 클래스
	 * ROLE_USER(유저)/ROLE_ADMIN(관리자)
	 * 
	 * @author YDH
	 *
	 */
	public enum AuthorityRole {
		ROLE_USER,
		ROLE_ADMIN;

		public String getValue() { return super.toString(); }
	}
	
	
	/**
	 * 주문 상태에 대한 enum 클래스
	 * CANCEL(취소)/EXCHANGE(교환)/RETURN(환불)/ORDER_COMPLETE(주문완료)/SHIPMENT_COMPLETE(배송완료)
	 * 
	 * @author YDH
	 *
	 */
	public enum OrdersStatus {
		CANCEL,
		EXCHANGE,
		RETURN,
		ORDER_COMPLETE,
		SHIPMENT_COMPLETE;
		
		public String getValue() { return super.toString(); }
	}
	
	
	/**
	 * 상품 진열 상태에 대한 enum 클래스
	 * MAIN(메인진열)/EVENT(이벤트진열)/NONE(해당사항없음)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductDisplayStatus {
		MAIN,
		EVENT,
		NONE;

		public String getValue() { return super.toString(); }
	}
	
	
	/**
	 * 상품 관리 상태에 대한 enum 클래스
	 * STOCK(재고에 따라 상품 판매 여부 결정)/NON_STOCK(재고에 상관 없이 판매)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductManageStatus {
		STOCK,
		NON_STOCK;
		
		public String getValue() { return super.toString(); }
	}
	
	
	/**
	 * 상품 이미지 상태에 대한 enum 클래스
	 * MAIN(메인이미지)/SUB(서브이미지)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductImageStatus {
		MAIN,
		SUB;
		
		public String getValue() { return super.toString(); }
	}
}
