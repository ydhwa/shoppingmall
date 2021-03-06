package com.cafe24.shoppingmall.dto;

import com.cafe24.shoppingmall.vo.Enum.ProductImageStatus;

/**
 * 상품 이미지 정보를 보여줄 DTO
 * 
 * @author YDH
 *
 */
public class ProductImageDto {
	private Long no;					// 상품이미지번호
	private String name;				// 파일명
	private String extension;			// 확장자명
	private String path;				// 저장경로
	private ProductImageStatus status;	// 이미지 상태(메인, 서브, ...)
	
	public ProductImageDto() {}
	
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
	public ProductImageStatus getStatus() {
		return status;
	}
	public void setStatus(ProductImageStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ProductImageDto [no=" + no + ", name=" + name + ", extension=" + extension + ", path=" + path
				+ ", status=" + status + "]";
	}
}
