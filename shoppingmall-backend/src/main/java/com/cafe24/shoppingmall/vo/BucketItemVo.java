package com.cafe24.shoppingmall.vo;

/**
 * 장바구니에 담기는 품목에 대한 VO
 * 
 * @author YDH
 *
 */
public class BucketItemVo {
	private Long memberNo;				// 회원번호
	private String identifier;			// 장바구니 식별자(비회원용)
	private Long productOptionItemNo;	// 품목번호
	private Integer quantity;			// 수량
	
	public BucketItemVo() {}
	public BucketItemVo(Long memberNo, String identifier, Long productOptionItemNo, Integer quantity) {
		this.memberNo = memberNo;
		this.identifier = identifier;
		this.productOptionItemNo = productOptionItemNo;
		this.quantity = quantity;
	}
	public BucketItemVo(Long memberNo, Long productOptionItemNo) {
		this.memberNo = memberNo;
		this.productOptionItemNo = productOptionItemNo;
	}
	public BucketItemVo(Long productOptionItemNo, Integer quantity) {
		this.productOptionItemNo = productOptionItemNo;
		this.quantity = quantity;
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
	public Long getProductOptionItemNo() {
		return productOptionItemNo;
	}
	public void setProductOptionItemNo(Long productOptionItemNo) {
		this.productOptionItemNo = productOptionItemNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BucketItemVo [memberNo=" + memberNo + ", identifier=" + identifier + ", productOptionItemNo="
				+ productOptionItemNo + ", quantity=" + quantity + "]";
	}
}
