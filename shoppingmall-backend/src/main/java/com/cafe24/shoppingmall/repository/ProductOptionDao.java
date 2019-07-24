package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionValueVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;

@Repository
public class ProductOptionDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public boolean insertOptions(List<ProductOptionVo> productOptionList) {
		int result = 0;
		for(ProductOptionVo productOptionVo: productOptionList) {
			if(1 == sqlSession.insert("productoption.insertName", productOptionVo)) {
				for(ProductOptionValueVo productOptionValueVo: productOptionVo.getProductOptionValueList()) {
					if(1 != sqlSession.insert("productoption.insertValue", productOptionValueVo)) {
						return false;
					}
				}
				result++;
			}
		}
		System.out.println("** 상품옵션 삽입: " + result);
		return 0 < result;
	}

	public boolean insertOptionItems(List<ProductOptionItemVo> productOptionItemList) {
		int result = sqlSession.insert("productoption.insertItems", productOptionItemList);
		System.out.println("** 품목 삽입: " + result);
		return 0 < result;
	}

}
