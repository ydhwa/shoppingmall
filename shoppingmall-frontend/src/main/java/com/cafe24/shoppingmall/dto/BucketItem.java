package com.cafe24.shoppingmall.dto;

/**
 * 장바구니에 담긴 상품 정보를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class BucketItem {
	private Integer sellPrice;				// 판매가
	private Integer quantity;				// 수량
	
	private Long productNo;					// 상품번호
	private String productName;				// 상품명

	private Long productOptionItemNo;		// 품목번호
	private String productOptionDetails;	// 품목상세
	
	private Long memberNo;					// (식별용) 회원번호
	private String identifier;				// (식별용) 식별자
	
	public BucketItem() {}
	
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
