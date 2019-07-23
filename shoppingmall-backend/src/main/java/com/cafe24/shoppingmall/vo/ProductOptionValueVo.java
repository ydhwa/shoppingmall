package com.cafe24.shoppingmall.vo;

/**
 * 옵션값에 대한 VO
 * 
 * @author YDH
 *
 */
public class ProductOptionValueVo {
	private Long no;		// 옵션값 번호
	private String value;	// 옵션값
	
	public ProductOptionValueVo() {}
	
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

	@Override
	public String toString() {
		return "ProductOptionValueVo [no=" + no + ", value=" + value + "]";
	}
}
