package com.cafe24.shoppingmall.vo;

/**
 * 장바구니에 담기는 품목에 대한 VO
 * 
 * @author YDH
 *
 */
public class BucketItemVo {
	private Long no;			// 장바구니에 담기는 품목 번호
	private Integer quantity;	// 수량
	private ProductOptionItemVo productOptionItem;	// 품목
	
	public BucketItemVo() {}
	
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
	public ProductOptionItemVo getProductOptionItem() {
		return productOptionItem;
	}
	public void setProductOptionItem(ProductOptionItemVo productOptionItem) {
		this.productOptionItem = productOptionItem;
	}
	
	@Override
	public String toString() {
		return "BucketItem [no=" + no + ", quantity=" + quantity + ", productOptionItem=" + productOptionItem + "]";
	}
}
