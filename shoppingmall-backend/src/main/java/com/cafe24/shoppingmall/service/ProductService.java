package com.cafe24.shoppingmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.CategoryDao;
import com.cafe24.shoppingmall.repository.ProductDao;
import com.cafe24.shoppingmall.repository.ProductOptionDao;
import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
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
	@Autowired
	private ProductOptionDao productOptionDao;
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * 상품등록
	 * 
	 * @param productMap 등록할 상품의 상품 정보
	 * @return 등록에 성공한 상품 정보
	 */
	@Transactional
	public boolean registProduct(Map<String, Object> productMap) {
		return 
				productDao.insert((ProductVo) productMap.get("product")) &&
				productOptionDao.insertNames((List<ProductOptionNameVo>) productMap.get("optionNameList")) &&
				productOptionDao.insertValues((List<ProductOptionValueVo>) productMap.get("optionValueList")) &&
				productOptionDao.insertItems((List<ProductOptionItemVo>) productMap.get("optionItemList")) &&
				categoryDao.addProductCategories((List<CategoryVo>) productMap.get("categoryList")) &&
				productDao.insertImages((List<ProductImageVo>) productMap.get("productImageList"));
	}

	public List<ProductVo> searchProducts(Map<String, Object> map) {
		return null;
	}

	public ProductVo getProduct(Long no) {
		return null;
	}

	public Boolean modifyProduct(ProductVo productVo) {
		return null;
	}

	public Boolean deleteProduct(Long no) {
		return null;
	}

}
