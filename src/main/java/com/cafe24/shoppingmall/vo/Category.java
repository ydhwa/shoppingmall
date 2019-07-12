package com.cafe24.shoppingmall.vo;

/**
 * 상품 카테고리에 대한 VO
 * 계층형 카테고리이다.
 * 
 * @author YDH
 *
 */
public class Category {
	private Long no;		// 카테고리 번호
	private String name;	// 카테고리명
	private Long productNo;	// 상품번호
	private Long parentNo;	// 상위 카테고리 번호
	
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
	
	@Override
	public String toString() {
		return "Category [no=" + no + ", name=" + name + ", productNo=" + productNo + ", parentNo=" + parentNo + "]";
	}	
}
