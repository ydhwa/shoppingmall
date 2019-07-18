package com.cafe24.shoppingmall.vo;

import java.util.List;

/**
 * 옵션명에 대한 VO.
 * 해당 옵션에 대한 옵션값들을 담을 리스트도 가지고 있도록 했다.
 * 
 * @author YDH
 *
 */
public class ProductOptionNameVo {
	private Long no;		// 옵션명 번호
	private String name;	// 옵션명
	private List<ProductOptionValueVo> productOptionValueList;	// 옵션값 리스트
	
	public ProductOptionNameVo() {}
		
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
	public List<ProductOptionValueVo> getProductOptionValueList() {
		return productOptionValueList;
	}
	public void setProductOptionValueList(List<ProductOptionValueVo> productOptionValueList) {
		this.productOptionValueList = productOptionValueList;
	}
	
	@Override
	public String toString() {
		return "ProductOptionNameVo [no=" + no + ", name=" + name + ", productOptionValueList=" + productOptionValueList
				+ "]";
	}
}
