package com.cafe24.shoppingmall.vo;

import com.cafe24.shoppingmall.vo.Enum.ProductImageStatus;

/**
 * 상품 이미지 등록/보이기 시 사용하는 상품 이미지 VO
 * 
 * @author YDH
 *
 */
public class ProductImageVo {
	private String name;
	private String extension;
	private String path;
	private ProductImageStatus status;
	
	public ProductImageVo() {}

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
		return "ProductImageVo [name=" + name + ", extension=" + extension + ", path=" + path + ", status=" + status
				+ "]";
	}
}
