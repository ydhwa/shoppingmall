package com.cafe24.shoppingmall.vo;

/**
 * 옵션에 따른 품목들에 대한 VO
 * 
 * @author YDH
 *
 */
public class ProductOptionItemVo {
	private Long no;					// 품목번호
	private Long productNo;				// 상품번호
	private String optionNameKeys;		// 옵션명테이블키 (인덱스값 넣는 법 말고는 마땅한 방법이 떠오르지 않음)
	private String optionValueKeys;		// 옵션값테이블키 (인덱스값 넣는 법 말고는 마땅한 방법이 떠오르지 않음)
	private String details;				// 옵션 세부내용
	private Integer additionalAmount;	// 추가금액
	private String availability;		// 판매여부(Y/N)
	private String manageStatus;	// 관리상태
	private Integer stockQuantity;		// 재고수량
	
	public ProductOptionItemVo() {}
	public ProductOptionItemVo(String optionNameKeys, String optionValueKeys, String details, Integer additionalAmount, String availability, String manageStatus, Integer stockQuantity) {
		this.optionNameKeys = optionNameKeys;
		this.optionValueKeys = optionValueKeys;
		this.details = details;
		this.additionalAmount = additionalAmount;
		this.availability = availability;
		this.manageStatus = manageStatus;
		this.stockQuantity = stockQuantity;
	}
	public ProductOptionItemVo(Long productNo, String optionNameKeys, String optionValueKeys, String details, Integer additionalAmount, String availability, String manageStatus, Integer stockQuantity) {
		this.productNo = productNo;
		this.optionNameKeys = optionNameKeys;
		this.optionValueKeys = optionValueKeys;
		this.details = details;
		this.additionalAmount = additionalAmount;
		this.availability = availability;
		this.manageStatus = manageStatus;
		this.stockQuantity = stockQuantity;
	}

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
	public String getManageStatus() {
		return manageStatus;
	}
	public void setProductManageStatus(String manageStatus) {
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
		return "ProductOptionItemVo [no=" + no + ", productNo=" + productNo + ", optionNameKeys=" + optionNameKeys
				+ ", optionValueKeys=" + optionValueKeys + ", details=" + details + ", additionalAmount="
				+ additionalAmount + ", availability=" + availability
				+ ", manageStatus=" + manageStatus + ", stockQuantity=" + stockQuantity + "]";
	}
}
