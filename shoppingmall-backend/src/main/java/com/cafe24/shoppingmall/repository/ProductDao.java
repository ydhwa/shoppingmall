package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductVo;

@Repository
public class ProductDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public boolean insert(ProductVo productVo) {
		int result = sqlSession.insert("product.insert", productVo);
		System.out.println("** 상품 삽입: " + result);
		return 1 == result;
	}

	public boolean insertImages(List<ProductImageVo> productImageList) {
		int result = sqlSession.insert("product.insertImages", productImageList);
		System.out.println("** 상품 이미지 삽입: " + result);
		return 0 < result;
	}

}
