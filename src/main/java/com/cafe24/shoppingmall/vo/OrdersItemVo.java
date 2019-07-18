package com.cafe24.shoppingmall.vo;

/**
 * 주문 품목에 대한 VO
 * 
 * @author YDH
 *
 */
public class OrdersItemVo {
	private Long no;			// 주문하는 품목 번호
	private Integer quantity;	// 수량
	
	private Integer productPrice;	// (기록용) 품목가격
	private String optionDetails;	// (기록용) 옵션 세부내용
	
	private ProductOptionItemVo productOptionItem;	// 품목
	
	public OrdersItemVo() {}
	
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
	public String getOptionDetails() {
		return optionDetails;
	}
	public void setOptionDetails(String optionDetails) {
		this.optionDetails = optionDetails;
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
				+ ", optionDetails=" + optionDetails + ", productOptionItem=" + productOptionItem + "]";
	}
}
