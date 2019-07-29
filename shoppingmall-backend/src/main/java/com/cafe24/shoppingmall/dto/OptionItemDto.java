package com.cafe24.shoppingmall.dto;

import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;

public class OptionItemDto {
	private Long no;					// 품목번호
	private Long productNo;				// 상품번호
	private String details;				// 옵션 세부내용
	private Integer additionalAmount;	// 추가금액
	private String availability;		// 판매여부(Y/N)
	private ProductManageStatus manageStatus;	// 관리상태
	private Integer stockQuantity;		// 재고수량
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Integer getAdditionalAmount() {
		return additionalAmount;
	}
	public void setAdditionalAmount(Integer additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public ProductManageStatus getManageStatus() {
		return manageStatus;
	}
	public void setManageStatus(ProductManageStatus manageStatus) {
		this.manageStatus = manageStatus;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	@Override
	public String toString() {
		return "OptionItemDto [no=" + no + ", productNo=" + productNo + ", details=" + details + ", additionalAmount="
				+ additionalAmount + ", availability=" + availability + ", manageStatus=" + manageStatus
				+ ", stockQuantity=" + stockQuantity + "]";
	}
}
