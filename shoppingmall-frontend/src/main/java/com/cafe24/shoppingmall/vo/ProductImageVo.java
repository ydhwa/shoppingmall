package com.cafe24.shoppingmall.vo;

/**
 * 상품 이미지에 대한 VO
 * 
 * @author YDH
 *
 */
public class ProductImageVo {
	private Long productNo;				// 상품번호
	private String name;				// 파일명
	private String extension;			// 확장자명
	private String path;				// 저장경로
	
	private String status;	// 상품 이미지 상태(메인, 서브, ...)
	
	public ProductImageVo() {}
	public ProductImageVo(String name, String extension, String path) {
		this.name = name;
		this.extension = extension;
		this.path = path;
	}
	public ProductImageVo(Long productNo, String name, String extension, String path) {
		this.productNo = productNo;
		this.name = name;
		this.extension = extension;
		this.path = path;
	}

	public Long getProductNo() {
		return productNo;
	}
	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String	 status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ProductImageVo [productNo=" + productNo + ", name=" + name + ", extension=" + extension + ", path="
				+ path + ", status=" + status + "]";
	}
}
