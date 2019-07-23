package com.cafe24.shoppingmall.vo;

import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;

/**
 * 옵션에 따른 상품들에 대한 VO
 * 
 * @author YDH
 *
 */
public class ProductOptionItemVo {
	private Long no;					// 품목번호
	private Long productNo;				// 상품번호
	private String optionNameKeys;		// 옵션명테이블키
	private String optionValueKeys;		// 옵션값테이블키
	private String details;				// 옵션 세부내용
	private Integer additionalAmount;	// 추가금액
	private String availability;		// 판매여부(Y/N)
	private ProductManageStatus manageStatus;	// 관리상태
	private String stockQuantity;		// 재고수량
	
	public ProductOptionItemVo() {}

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
	public String getOptionNameKeys() {
		return optionNameKeys;
	}
	public void setOptionNameKeys(String optionNameKeys) {
		this.optionNameKeys = optionNameKeys;
	}
	public String getOptionValueKeys() {
		return optionValueKeys;
	}
	public void setOptionValueKeys(String optionValueKeys) {
		this.optionValueKeys = optionValueKeys;
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
	public void setProductManageStatus(ProductManageStatus manageStatus) {
		this.manageStatus = manageStatus;
	}
	public String getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(String stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "ProductOptionItemVo [no=" + no + ", productNo=" + productNo + ", optionNameKeys=" + optionNameKeys
				+ ", optionValueKeys=" + optionValueKeys + ", details=" + details + ", additionalAmount="
				+ additionalAmount + ", availability=" + availability
				+ ", manageStatus=" + manageStatus + ", stockQuantity=" + stockQuantity + "]";
	}
}
