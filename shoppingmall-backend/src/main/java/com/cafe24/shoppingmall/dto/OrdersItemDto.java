package com.cafe24.shoppingmall.dto;

public class OrdersItemDto {
	private Long no;			// 주문하는 품목번호
	private Integer quantity;	// 수량

	private Long productNo;
	private String productCode;
	private String productName;
	
	private Long productOptionItemNo;
	private String productOptionItemDetails;
	
	private Integer sellPrice;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductNo() {
		return productNo;
	}

	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductOptionItemNo() {
		return productOptionItemNo;
	}

	public void setProductOptionItemNo(Long productOptionItemNo) {
		this.productOptionItemNo = productOptionItemNo;
	}

	public String getProductOptionItemDetails() {
		return productOptionItemDetails;
	}

	public void setProductOptionItemDetails(String productOptionItemDetails) {
		this.productOptionItemDetails = productOptionItemDetails;
	}

	public Integer getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "OrdersItemDto [no=" + no + ", quantity=" + quantity + ", productNo=" + productNo + ", productCode="
				+ productCode + ", productName=" + productName + ", productOptionItemNo=" + productOptionItemNo
				+ ", productOptionItemDetails=" + productOptionItemDetails + ", sellPrice=" + sellPrice + "]";
	}
}
