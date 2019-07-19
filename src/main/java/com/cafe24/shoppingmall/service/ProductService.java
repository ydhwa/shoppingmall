package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.ProductDao;
import com.cafe24.shoppingmall.vo.ProductOptionNameVo;
import com.cafe24.shoppingmall.vo.ProductOptionValueVo;
import com.cafe24.shoppingmall.vo.ProductVo;

/**
 * 상품에 대한 로직이 담긴 서비스
 * 
 * @author YDH
 *
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	/**
	 * 상품등록
	 * 
	 * @param productVo 등록할 상품의 상품 정보
	 * @return 등록에 성공한 상품 정보
	 */
	@Transactional
	public ProductVo registerProduct(ProductVo productVo) {
		List<ProductOptionNameVo> productOptionNameVoList = productVo.getProductOptionList();
		List<ProductOptionValueVo> productOptionValueVoList = new ArrayList<ProductOptionValueVo>();
		for(ProductOptionNameVo productOptionNameVo: productOptionNameVoList) {
			productOptionValueVoList.addAll(productOptionNameVo.getProductOptionValueList());
		}
		
//		productDao.insert(productVo);
//		productOptionNameDao.insert(productOptionNameVoList);
//		productOptionValueDao.insert(productOptionValueVoList);
//		productOptionItemDao.insert(productVo.getProductOptionItemList());
//		categoryDao.addProductCategory(productVo.getCategoryList());
//		productImageDao.insert(productVo.getProductImageList());
		
		return null;
	}

}
