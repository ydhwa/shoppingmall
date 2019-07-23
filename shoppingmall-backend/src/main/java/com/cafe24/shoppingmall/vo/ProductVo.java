package com.cafe24.shoppingmall.vo;

import java.util.List;

import com.cafe24.shoppingmall.vo.Enum.ProductDisplayStatus;
import com.cafe24.shoppingmall.vo.Enum.ProductManageStatus;

/**
 * 상품에 대한 VO
 * 
 * @author YDH
 *
 */
public class ProductVo {
	private Long no;									// 상품번호
	private String code;								// 상품코드
	private String name;								// 상품명
	private Integer supplyPrice;						// 공급가
	private Integer sellPrice;							// 판매가(소비자가)
	private String summaryDescription;					// 요약설명
	private String detailedDescription;					// 상세설명
	private String regDate;								// 등록일
	private ProductDisplayStatus productDisplayStatus;	// 진열상태
	private Boolean availability;						// 판매여부
	private String buyNowUrl;							// 바로구매URL
	private Double weight;								// 상품 하나 당 중량
	private ProductManageStatus productManageStatus;	// 관리상태
	private Integer stockQuantity;						// 재고수량
	private Boolean optionAvailable;					// 옵션사용여부
	
	private List<ProductOptionNameVo> productOptionList;		// 상품 하나의 옵션들
	private List<ProductOptionItemVo> productOptionItemList;	// 옵션에 따른 상품들
	private List<List<CategoryVo>> categoryList;				// 카테고리들(여러 카테고리/복수 카테고리)
	private List<ProductImageVo> productImageList;				// 상품 이미지들
	
	public ProductVo() {}

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
	public ProductDisplayStatus getProductDisplayStatus() {
		return productDisplayStatus;
	}
	public void setProductDisplayStatus(ProductDisplayStatus productDisplayStatus) {
		this.productDisplayStatus = productDisplayStatus;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	public String getBuyNowUrl() {
		return buyNowUrl;
	}
	public void setBuyNowUrl(String buyNowUrl) {
		this.buyNowUrl = buyNowUrl;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public ProductManageStatus getProductManageStatus() {
		return productManageStatus;
	}
	public void setProductManageStatus(ProductManageStatus productManageStatus) {
		this.productManageStatus = productManageStatus;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Boolean getOptionAvailable() {
		return optionAvailable;
	}
	public void setOptionAvailable(Boolean optionAvailable) {
		this.optionAvailable = optionAvailable;
	}
	public List<ProductOptionNameVo> getProductOptionList() {
		return productOptionList;
	}
	public void setProductOptionList(List<ProductOptionNameVo> productOptionList) {
		this.productOptionList = productOptionList;
	}
	public List<ProductOptionItemVo> getProductOptionItemList() {
		return productOptionItemList;
	}
	public void setProductOptionItemList(List<ProductOptionItemVo> productOptionItemList) {
		this.productOptionItemList = productOptionItemList;
	}
	public List<List<CategoryVo>> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<List<CategoryVo>> categoryList) {
		this.categoryList = categoryList;
	}
	public List<ProductImageVo> getProductImageList() {
		return productImageList;
	}
	public void setProductImageList(List<ProductImageVo> productImageList) {
		this.productImageList = productImageList;
	}

	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", code=" + code + ", name=" + name + ", supplyPrice=" + supplyPrice
				+ ", sellPrice=" + sellPrice + ", summaryDescription=" + summaryDescription + ", detailedDescription="
				+ detailedDescription + ", regDate=" + regDate + ", productDisplayStatus=" + productDisplayStatus
				+ ", availability=" + availability + ", buyNowUrl=" + buyNowUrl + ", weight=" + weight
				+ ", productManageStatus=" + productManageStatus + ", stockQuantity=" + stockQuantity
				+ ", optionAvailable=" + optionAvailable + ", productOptionList=" + productOptionList
				+ ", productOptionItemList=" + productOptionItemList + ", categoryList=" + categoryList
				+ ", productImageList=" + productImageList + "]";
	}
}