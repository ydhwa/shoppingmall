package com.cafe24.shoppingmall.vo;

/**
 * 장바구니 관련 작업 시 사용하는 장바구니 VO
 * 
 * @author YDH
 *
 */
public class BucketVo {
	private Long no;
	private Integer quantity;
	private String sessionId;
	private Long memberNo;
	private Long productOptionItemNo;
	private String expDate;
	
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getProductOptionItemNo() {
		return productOptionItemNo;
	}
	public void setProductOptionItemNo(Long productOptionItemNo) {
		this.productOptionItemNo = productOptionItemNo;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	@Override
	public String toString() {
		return "BucketVo [no=" + no + ", quantity=" + quantity + ", sessionId=" + sessionId + ", memberNo=" + memberNo
				+ ", productOptionItemNo=" + productOptionItemNo + ", expDate=" + expDate + "]";
	}
}
