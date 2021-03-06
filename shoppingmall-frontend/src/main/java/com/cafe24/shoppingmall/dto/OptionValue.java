package com.cafe24.shoppingmall.dto;

/**
 * 옵션값을 보여줄 DTO
 * 옵션 DTO의 리스트에 담긴다.
 * 
 * @author YDH
 *
 */
public class OptionValue {
	private Long no;		// 옵션값 번호
	private String value;	// 옵션값
	private Long index;		// 이 옵션값이 몇 번째에 해당하는지
	
	public OptionValue() {}
	
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
