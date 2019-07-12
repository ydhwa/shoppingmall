package com.cafe24.shoppingmall.vo;

/**
 * 옵션에 따른 상품들에 대한 VO
 * 
 * @author YDH
 *
 */
public class OrdersProductOption {
	private Long no;					// 품목번호
	private Long productNo;				// 상품번호
	private String contents;			// 옵션내용(옵션명-옵션값/옵션명-옵션값/...)
	private Integer additionalAmount;	// 추가금액
	private String stockQuantity;		// 재고수량
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getAdditionalAmount() {
		return additionalAmount;
	}
	public void setAdditionalAmount(Integer additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
	public String getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(String stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	@Override
	public String toString() {
		return "OrdersProductOption [no=" + no + ", productNo=" + productNo + ", contents=" + contents
				+ ", additionalAmount=" + additionalAmount + ", stockQuantity=" + stockQuantity + "]";
	}
}
