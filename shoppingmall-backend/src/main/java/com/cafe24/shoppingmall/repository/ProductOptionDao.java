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
				// 옵션을 사용하지 않는 경우, 디폴트 옵션명은 생기고 옵션값 리스트는 만들어지지 않는다.
				if(productOptionVo.getProductOptionValueList() != null) {
					for(ProductOptionValueVo productOptionValueVo: productOptionVo.getProductOptionValueList()) {
						if(1 != sqlSession.insert("productoption.insertValue", productOptionValueVo)) {
							return false;
						}
					}
				}
				result++;
			}
		}
		return productOptionList.size() == result;
	}

	public boolean insertOptionItems(List<ProductOptionItemVo> productOptionItemList) {
		int result = 0;
		for(ProductOptionItemVo productOptionItemVo: productOptionItemList) {
			result += sqlSession.insert("productoption.insertItem", productOptionItemVo);
		}
		return productOptionItemList.size() == result;
	}

}
