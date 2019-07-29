package com.cafe24.shoppingmall.dto;

import java.util.List;

import com.cafe24.shoppingmall.vo.Enum.ProductDisplayStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;

public class ProductSummaryDto {
	private Long no;							// 상품번호
	private String code;						// 상품코드
	private String name;						// 상품명
	private Integer sellPrice;					// 판매가(소비자가)
	private String regDate;						// 등록일
	private String delStatus;					// 삭제여부(Y/N)
	private ProductDisplayStatus displayStatus;	// 진열상태
	private String availability;				// 판매여부(Y/N)
	private ProductManageStatus manageStatus;	// 관리상태
	private List<CategoryDto> categoryList;		// 카테고리 리스트
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
	public ProductDisplayStatus getDisplayStatus() {
		return displayStatus;
	}
	public void setDisplayStatus(ProductDisplayStatus displayStatus) {
		this.displayStatus = displayStatus;
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
	public void setManageStatus(ProductManageStatus manageStatus) {
		this.manageStatus = manageStatus;
	}
	public List<CategoryDto> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryDto> categoryList) {
		this.categoryList = categoryList;
	}
	@Override
	public String toString() {
		return "ProductSummaryDto [no=" + no + ", code=" + code + ", name=" + name + ", sellPrice=" + sellPrice
				+ ", regDate=" + regDate + ", delStatus=" + delStatus + ", displayStatus=" + displayStatus
				+ ", availability=" + availability + ", manageStatus=" + manageStatus + ", categoryList=" + categoryList
				+ "]";
	}
}
