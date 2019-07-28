package com.cafe24.shoppingmall.dto;

public class OrdersSummaryDto {
	private Long ordersNo;
	private String ordersCode;
	private String ordersDate;
	private String ordersStatus;
	private Integer ordersTotalOrderAccount;
	private String details;
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
