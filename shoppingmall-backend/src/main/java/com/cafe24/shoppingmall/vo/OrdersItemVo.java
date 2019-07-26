package com.cafe24.shoppingmall.vo;

/**
 * 주문 품목에 대한 VO
 * 
 * @author YDH
 *
 */
public class OrdersItemVo {
	private Long no;			// 주문하는 품목번호
	private Integer quantity;	// 수량
	
	private Integer productPrice;	// (기록용) 품목가격
	private String optionContents;	// (기록용) 옵션 세부내용
	private Long productNo;			// (기록용) 상품번호
	
	private ProductOptionItemVo productOptionItem;	// 품목
	
	public OrdersItemVo() {}
	public OrdersItemVo(Integer quantity, Long productNo) {
		this.quantity = quantity;
		this.productNo = productNo;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public String getOptionContents() {
		return optionContents;
	}
	public void setOptionContents(String optionContents) {
		this.optionContents = optionContents;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public ProductOptionItemVo getProductOptionItem() {
		return productOptionItem;
	}
	public void setProductOptionItem(ProductOptionItemVo productOptionItem) {
		this.productOptionItem = productOptionItem;
	}

	@Override
	public String toString() {
		return "OrdersItemVo [no=" + no + ", quantity=" + quantity + ", productPrice=" + productPrice
				+ ", optionContents=" + optionContents + ", productNo=" + productNo + ", productOptionItem="
				+ productOptionItem + "]";
	}
}
