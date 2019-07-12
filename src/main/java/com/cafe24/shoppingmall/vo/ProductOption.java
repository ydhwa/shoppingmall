package com.cafe24.shoppingmall.vo;

/**
 * 상품 옵션에 관한 VO
 * 옵션명-값 으로 이루어져 있다.
 * 
 * @author YDH
 *
 */
public class ProductOption {
	private Long productNo;
	private String name;
	private String value;
	
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ProductOption [productNo=" + productNo + ", name=" + name + ", value=" + value + "]";
	}
}
