package com.cafe24.shoppingmall.dto;

public class BucketItemDto {
	private Integer sellPrice;
	private Integer quantity;
	
	private Long productNo;
	private String productName;

	private Long productOptionItemNo;
	private String productOptionDetails;
	
	private Long memberNo;
	private String identifier;
	
	public Long getProductOptionItemNo() {
		return productOptionItemNo;
	}
	public void setProductOptionItemNo(Long productOptionItemNo) {
		this.productOptionItemNo = productOptionItemNo;
	}
	public Integer getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getProductOptionDetails() {
		return productOptionDetails;
	}
	public void setProductOptionDetails(String productOptionDetails) {
		this.productOptionDetails = productOptionDetails;
	}
	@Override
	public String toString() {
		return "BucketItemDto [sellPrice=" + sellPrice + ", quantity=" + quantity + ", productNo=" + productNo
				+ ", productName=" + productName + ", productOptionItemNo=" + productOptionItemNo
				+ ", productOptionDetails=" + productOptionDetails + ", memberNo=" + memberNo + ", identifier="
				+ identifier + "]";
	}
}
