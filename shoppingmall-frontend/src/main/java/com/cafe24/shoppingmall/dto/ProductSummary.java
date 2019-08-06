package com.cafe24.shoppingmall.dto;

/**
 * 상품 검색결과를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class ProductSummary {
	private Long no;							// 상품번호
	private String code;						// 상품코드
	private String name;						// 상품명
	private Integer sellPrice;					// 판매가(소비자가)
	private String summaryDescription;			// 요약설명
	private String regDate;						// 등록일
	private String delStatus;					// 삭제여부(Y/N)
	private String displayStatus;				// 진열상태
	private String availability;				// 판매여부(Y/N)
	private String manageStatus;				// 관리상태
	private String categoryList;				// 카테고리 리스트(문자열화시킴)
	
	public ProductSummary() {}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	public String getDisplayStatus() {
		return displayStatus;
	}
	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
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
	public void setManageStatus(String manageStatus) {
		this.manageStatus = manageStatus;
	}
	public String getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(String categoryList) {
		this.categoryList = categoryList;
	}
	public String getSummaryDescription() {
		return summaryDescription;
	}
	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}

	@Override
	public String toString() {
		return "ProductSummary [no=" + no + ", code=" + code + ", name=" + name + ", sellPrice=" + sellPrice
				+ ", summaryDescription=" + summaryDescription + ", regDate=" + regDate + ", delStatus=" + delStatus
				+ ", displayStatus=" + displayStatus + ", availability=" + availability + ", manageStatus="
				+ manageStatus + ", categoryList=" + categoryList + "]";
	}
}
