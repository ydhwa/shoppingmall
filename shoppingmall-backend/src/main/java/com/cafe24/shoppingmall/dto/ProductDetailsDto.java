package com.cafe24.shoppingmall.dto;

import java.util.List;

import com.cafe24.shoppingmall.vo.Enum.ProductDisplayStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;

/**
 * 상품 상세 정보를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class ProductDetailsDto {
	private Long no;							// 상품번호
	private String code;						// 상품코드
	private String name;						// 상품명
	private Integer supplyPrice;				// 공급가
	private Integer sellPrice;					// 판매가(소비자가)
	private String summaryDescription;			// 요약설명
	private String detailedDescription;			// 상세설명
	private String regDate;						// 등록일
	private Double weight;						// 상품 하나 당 중량
	private String optionAvailable;				// 옵션사용여부(Y/N)
	private String delStatus;					// 삭제여부(Y/N)
	private ProductDisplayStatus displayStatus;	// 진열상태
	private String availability;				// 판매여부(Y/N)
	private ProductManageStatus manageStatus;	// 관리상태
	private Integer stockQuantity;				// 재고수량
	
	private List<OptionDto> optionList;					// 옵션 리스트
	private List<OptionItemDto> optionItemList;			// 품목 리스트
	private List<CategoryDto> categoryList;				// 카테고리 리스트
	private List<ProductImageDto> productImageList;		// 상품 이미지 리스트
	
	public ProductDetailsDto() {}
	
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
	public Integer getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(Integer supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public Integer getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getSummaryDescription() {
		return summaryDescription;
	}
	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getOptionAvailable() {
		return optionAvailable;
	}
	public void setOptionAvailable(String optionAvailable) {
		this.optionAvailable = optionAvailable;
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
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public List<OptionDto> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<OptionDto> optionList) {
		this.optionList = optionList;
	}
	public List<OptionItemDto> getOptionItemList() {
		return optionItemList;
	}
	public void setOptionItemList(List<OptionItemDto> optionItemList) {
		this.optionItemList = optionItemList;
	}
	public List<CategoryDto> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryDto> categoryList) {
		this.categoryList = categoryList;
	}
	public List<ProductImageDto> getProductImageList() {
		return productImageList;
	}
	public void setProductImageList(List<ProductImageDto> productImageList) {
		this.productImageList = productImageList;
	}
	
	@Override
	public String toString() {
		return "ProductDetailsDto [no=" + no + ", code=" + code + ", name=" + name + ", supplyPrice=" + supplyPrice
				+ ", sellPrice=" + sellPrice + ", summaryDescription=" + summaryDescription + ", detailedDescription="
				+ detailedDescription + ", regDate=" + regDate + ", weight=" + weight + ", optionAvailable="
				+ optionAvailable + ", delStatus=" + delStatus + ", displayStatus=" + displayStatus + ", availability="
				+ availability + ", manageStatus=" + manageStatus + ", stockQuantity=" + stockQuantity + ", optionList="
				+ optionList + ", optionItemList=" + optionItemList + ", categoryList=" + categoryList
				+ ", productImageList=" + productImageList + "]";
	}
}
