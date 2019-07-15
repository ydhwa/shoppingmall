package com.cafe24.shoppingmall.vo;

/**
 * 상품 이미지 등록/보이기 시 사용하는 상품 이미지 VO
 * 
 * @author YDH
 *
 */
public class ProductImageVo {
	private String saveName;
	private String extension;
	private String savePath;
	private ProductImageStatus status;
	private Long productNo;
	
	public ProductImageVo() {}
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public ProductImageStatus getStatus() {
		return status;
	}
	public void setStatus(ProductImageStatus status) {
		this.status = status;
	}
	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	
	@Override
	public String toString() {
		return "ProductImageVo [saveName=" + saveName + ", extension=" + extension + ", savePath=" + savePath
				+ ", productNo=" + productNo + "]";
	}
	
	/**
	 * 상품 이미지 상태에 대한 enum 클래스
	 * MAIN(메인이미지)/SUB(서브이미지)
	 * 
	 * @author YDH
	 *
	 */
	public enum ProductImageStatus {
		MAIN("MAIN"),
		SUB("SUB");
		
		private String str;
		ProductImageStatus(String str) { this.str = str; }
		public String toString() { return str; }
	}
}
