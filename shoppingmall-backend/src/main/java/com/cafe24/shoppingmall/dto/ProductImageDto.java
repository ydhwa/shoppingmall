package com.cafe24.shoppingmall.dto;

import com.cafe24.shoppingmall.vo.Enum.ProductImageStatus;

public class ProductImageDto {
	private Long no;
	private String name;
	private String extension;
	private String path;
	private ProductImageStatus status;
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
