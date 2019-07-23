package com.cafe24.shoppingmall.vo;

/**
 * 회원 배송지 관리를 위한 배송지 VO
 * 
 * @author YDH
 *
 */
public class ShippingAddressVo {
	private Long no;
	private String postalCode;
	private String baseAddress;
	private String detailAddress;
	private String description;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
		return "ShippingAddressVo [no=" + no + ", postalCode=" + postalCode + ", baseAddress=" + baseAddress
				+ ", detailAddress=" + detailAddress + ", description=" + description + "]";
	}
}