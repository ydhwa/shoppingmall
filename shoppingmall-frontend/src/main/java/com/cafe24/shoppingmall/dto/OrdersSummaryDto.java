package com.cafe24.shoppingmall.dto;

/**
 * 주문 검색 결과 정보를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class OrdersSummaryDto {
	private Long ordersNo;						// 주문번호
	private String ordersCode;					// 주문코드
	private String ordersDate;					// 주문일자
	private String ordersStatus;				// 주문상태
	private Integer ordersTotalOrderAccount;	// 총 주문금액
	private String details;						// "[상품명] 외 N건"
	
	public OrdersSummaryDto() {}
	
	public Long getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(Long ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getOrdersCode() {
		return ordersCode;
	}
	public void setOrdersCode(String ordersCode) {
		this.ordersCode = ordersCode;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getOrdersStatus() {
		return ordersStatus;
	}
	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
	}
	public Integer getOrdersTotalOrderAccount() {
		return ordersTotalOrderAccount;
	}
	public void setOrdersTotalOrderAccount(Integer ordersTotalOrderAccount) {
		this.ordersTotalOrderAccount = ordersTotalOrderAccount;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return "OrdersSummaryDto [ordersNo=" + ordersNo + ", ordersCode=" + ordersCode + ", ordersDate=" + ordersDate
				+ ", ordersStatus=" + ordersStatus + ", ordersTotalOrderAccount=" + ordersTotalOrderAccount
				+ ", details=" + details + "]";
	}
}
