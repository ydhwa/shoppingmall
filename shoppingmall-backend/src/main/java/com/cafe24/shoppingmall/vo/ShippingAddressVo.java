package com.cafe24.shoppingmall.vo;

/**
 * (회원용)배송지에 대한 VO
 * 
 * @author YDH
 *
 */
public class ShippingAddressVo {
	private Long no;				// 번호
	private Long memberNo;			// 회원번호
	private String postalCode;		// 우편번호
	private String baseAddress;		// 기본주소
	private String detailAddress;	// 상세주소
	private String description;		// 배송지에 대한 설명
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ShippingAddressVo [no=" + no + ", memberNo=" + memberNo + ", postalCode=" + postalCode
				+ ", baseAddress=" + baseAddress + ", detailAddress=" + detailAddress + ", description=" + description
				+ "]";
	}
}
