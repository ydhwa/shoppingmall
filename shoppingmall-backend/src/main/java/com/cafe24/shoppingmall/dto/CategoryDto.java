package com.cafe24.shoppingmall.dto;

public class CategoryDto {
	private Long no;		// 카테고리 번호
	private String name;	// 카테고리명
	private Long productNo;	// 상품번호
	private Long parentNo;	// 상위 카테고리 번호
	private Integer level;	// 계층 단계 표시 (depth와 비슷함)
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public Long getParentNo() {
		return parentNo;
	}
	public void setParentNo(Long parentNo) {
		this.parentNo = parentNo;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "CategoryDto [no=" + no + ", name=" + name + ", productNo=" + productNo + ", parentNo=" + parentNo
				+ ", level=" + level + "]";
	}
}
