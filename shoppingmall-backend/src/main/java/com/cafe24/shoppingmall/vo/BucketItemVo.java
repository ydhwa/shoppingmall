package com.cafe24.shoppingmall.vo;

/**
 * 장바구니에 담기는 품목에 대한 VO
 * 
 * @author YDH
 *
 */
public class BucketItemVo {
	private Long no;			// 장바구니에 담기는 품목 번호
	private String identifier;	// 비회원용 장바구니 식별자
	private Integer quantity;	// 수량
	private String regDate;		// 등록일
	
	private MemberVo memberNo;	// 회원
	private ProductOptionItemVo productOptionItem;	// 품목
	
	public BucketItemVo() {}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public MemberVo getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(MemberVo memberNo) {
		this.memberNo = memberNo;
	}
	public ProductOptionItemVo getProductOptionItem() {
		return productOptionItem;
	}
	public void setProductOptionItem(ProductOptionItemVo productOptionItem) {
		this.productOptionItem = productOptionItem;
	}

	@Override
	public String toString() {
		return "BucketItemVo [no=" + no + ", identifier=" + identifier + ", quantity=" + quantity + ", regDate="
				+ regDate + ", memberNo=" + memberNo + ", productOptionItem=" + productOptionItem + "]";
	}
}
