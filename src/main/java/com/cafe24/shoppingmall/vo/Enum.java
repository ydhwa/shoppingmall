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
		ENABLE("ENABLE"),
		DISABLE("DISABLE");

		private String str;
		MemberStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	
	
	/**
	 * 고객 권한 역할에 대한 enum 클래스
	 * ROLE_USER(유저)/ROLE_ADMIN(관리자)
	 * 
	 * @author YDH
	 *
	 */
	public enum AuthorityRole {
		USER("ROLE_USER"),
		ADMIN("ROLE_ADMIN");

		private String str;
		AuthorityRole(String str) { this.str = str; }
		public String toString() { return str; }
	}
	
	
	/**
	 * 주문 상태에 대한 enum 클래스
	 * CANCEL(취소)/EXCHANGE(교환)/RETURN(환불)/ORDER_COMPLETE(주문완료)/SHIPMENT_COMPLETE(배송완료)
	 * 
	 * @author YDH
	 *
	 */
	public enum OrdersStatus {
		CANCEL("CANCEL"),
		EXCHANGE("EXCHANGE"),
		RETURN("RETURN"),
		ORDER_COMPLETE("ORDER_COMPLETE"),
		SHIPMENT_COMPLETE("SHIPMENT_COMPLETE");
		
		private String str;
		OrdersStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	
	
	/**
	 * 상품 진열 상태에 대한 enum 클래스
	 * MAIN(메인진열)/EVENT(이벤트진열)/NONE(해당사항없음)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductDisplayStatus {
		MAIN("MAIN"),
		EVENT("EVENT"),
		NONE("NONE");

		private String str;
		ProductDisplayStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	
	
	/**
	 * 상품 관리 상태에 대한 enum 클래스
	 * STOCK(재고에 따라 상품 판매 여부 결정)/NON_STOCK(재고에 상관 없이 판매)/DISPLAY(진열되어 있는 상품 항목 중 하나라도 전부 팔리면 곧장 판매 중지)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductManageStatus {
		STOCK("STOCK"),
		NON_STOCK("NON_STOCK"),
		DISPLAY("DISPLAY");
		
		private String str;
		ProductManageStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
	
	
	/**
	 * 상품 이미지 상태에 대한 enum 클래스
	 * MAIN(메인이미지)/SUB(서브이미지)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductImageStatus {
		MAIN("MAIN"),
		SUB("SUB");
		
		private String str;
		ProductImageStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
}
