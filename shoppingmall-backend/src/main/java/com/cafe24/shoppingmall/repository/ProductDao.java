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
		return 1 == result;
	}

	public boolean insertImages(List<ProductImageVo> productImageList) {
		int result = 0;
		for(ProductImageVo productImageVo: productImageList) {
			result += sqlSession.insert("product.insertImage", productImageVo);
		}
		return productImageList.size() == result;
	}

}
