package com.cafe24.shoppingmall.dto;

public class OptionValueDto {
	private Long no;		// 옵션값 번호
	private String value;	// 옵션값
	private Long index;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "OptionValueDto [no=" + no + ", value=" + value + ", index=" + index + "]";
	}
}
