package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductOptionValueVo;

@Repository
public class ProductOptionDao {

	public boolean insertNames(List<ProductOptionVo> productOptionNameVoList) {
		return false;
	}

	public boolean insertValues(List<ProductOptionValueVo> productOptionValueVoList) {
		return false;
	}

	public boolean insertItems(List<ProductOptionItemVo> productOptionItemList) {
		return false;
	}

}
