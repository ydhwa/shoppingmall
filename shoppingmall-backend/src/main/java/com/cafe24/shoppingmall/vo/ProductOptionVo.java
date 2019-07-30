package com.cafe24.shoppingmall.vo;

import java.util.List;

/**
 * 옵션에 대한 VO.
 * product_option_name 테이블에 대응하는 VO이다.
 * 옵션명을 기본적으로 포함하고, 해당 옵션명에 대한 옵션값들을 담을 리스트도 가지고 있도록 했다.
 * 
 * @author YDH
 *
 */
public class ProductOptionVo {
	private Long no;		// 옵션명 번호
	private String name;	// 옵션명
	private List<ProductOptionValueVo> productOptionValueList;	// 옵션값 리스트
	private Long productNo;	// 상품번호
	
	public ProductOptionVo() {}
	public ProductOptionVo(String name, List<ProductOptionValueVo> productOptionValueList) {
		this.name = name;
		this.productOptionValueList = productOptionValueList;
	}
	public ProductOptionVo(String name, List<ProductOptionValueVo> productOptionValueList, Long productNo) {
		this.name = name;
		this.productOptionValueList = productOptionValueList;
		this.productNo = productNo;
	}
		
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
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	
	@Override
	public String toString() {
		return "ProductOptionVo [no=" + no + ", name=" + name + ", productOptionValueList=" + productOptionValueList
				+ ", productNo=" + productNo + "]";
	}
}
