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
import com.cafe24.shoppingmall.vo.ProductOptionVo;
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
	public Boolean registProduct(ProductVo product, List<ProductOptionVo> productOptionList, List<ProductOptionItemVo> productOptionItemList, List<CategoryVo> categoryList, List<ProductImageVo> productImageList) {
		if(!productDao.insert(product)) {
			return false;
		}
		if(!productOptionDao.insertOptions(productOptionList)) {
			return false;
		}
		if(!productOptionDao.insertOptionItems(productOptionItemList)) {
			return false;
		}
		// 나머지 항목은 없을 수도 있는 item들이므로, 우선 존재 여부를 체크한 후 존재하는 경우에만 insert 작업을 진행한다.
		if(categoryList != null && !categoryDao.addProductCategories(categoryList)) {
			return false;
		}
		if(productImageList != null && !productDao.insertImages(productImageList)) {
			return false;
		}

		return true;
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
