package com.cafe24.shoppingmall.dto;

import java.util.List;

/**
 * 옵션(옵션명과 옵션명에 해당하는 옵션값 리스트) 정보를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class OptionDto {
	private Long no;		// 옵션명 번호
	private String name;	// 옵션명
	private List<OptionValueDto> productOptionValueList;	// 옵션값 리스트
	private Long index;		// 이 옵션이 몇 번째에 해당하는지
	
	public OptionDto() {}
	
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
	public List<OptionValueDto> getProductOptionValueList() {
		return productOptionValueList;
	}
	public void setProductOptionValueList(List<OptionValueDto> productOptionValueList) {
		this.productOptionValueList = productOptionValueList;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "OptionDto [no=" + no + ", name=" + name + ", productOptionValueList=" + productOptionValueList
				+ ", index=" + index + "]";
	}
}
