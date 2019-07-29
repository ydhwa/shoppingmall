package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.repository.BucketItemDao;
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
	@Autowired
	private BucketItemDao bucketItemDao;
	
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

	public List<ProductSummaryDto> searchProductsToAdmin(HashMap<String, String> paramMap) {
		return productDao.getSearchListToAdmin(paramMap);
	}

	public ProductDetailsDto showProductToAdmin(Long no) {
		ProductDetailsDto product = productDao.getOneToAdmin(no);
		product.setOptionList(productOptionDao.getListByProductNo(no));
		product.setOptionItemList(productOptionDao.getItemListByProductNo(no));
		product.setCategoryList(categoryDao.getListByProductNo(no));
		product.setProductImageList(productDao.getImageListByNo(no));
		
		return product;
	}

	public Boolean modifyProductToAdmin(ProductVo product, List<ProductOptionVo> productOptionList, List<ProductOptionItemVo> productOptionItemList, List<CategoryVo> categoryList, List<ProductImageVo> productImageList) {
		if(!productDao.update(product)) {
			return false;
		}
		// 옵션은 싹 지웠다가 다시 등록하는 방식으로 흘러가게 한다.
		if(!productOptionDao.deleteOptions(product.getNo())) {
			return false;
		}
		if(!productOptionDao.insertOptions(productOptionList)) {
			return false;
		}
		
		// 옵션 삭제 전, 장바구니에 담겨 있던 품목들은 전부 삭제해야 한다.
		if(!bucketItemDao.deleteItemsByProductNo(product.getNo())) {
			return false;
		}
		if(!productOptionDao.deleteOptionItems(product.getNo())) {
			return false;
		}
		if(!productOptionDao.insertOptionItems(productOptionItemList)) {
			return false;
		}
		
		// 나머지 항목은 없을 수도 있는 item들이므로, 우선 존재 여부를 체크한 후 존재하는 경우에만 삭제 후 삽입 작업을 진행한다.
		if(categoryList != null && (categoryList.size() > 0 && !categoryDao.deleteProductCategories(product.getNo())) && !categoryDao.addProductCategories(categoryList)) {
			return false;
		}
		if(productImageList != null && (productImageList.size() > 0 && !productDao.deleteImages(product.getNo())) && !productDao.insertImages(productImageList)) {
			return false;
		}

		return true;
	}

	public Boolean deleteProductToAdmin(Long no) {
		// product 정보 제외하고 나머지 연관된 정보는 전부 지워버린다.
		// 카테고리, 장바구니, 옵션, 품목, 상품 이미지
		if(!categoryDao.deleteProductCategories(no)) {
			return false;
		}
		if(!bucketItemDao.deleteItemsByProductNo(no)) {
			return false;
		}
		if(!productOptionDao.deleteOptions(no)) {
			return false;
		}
		if(!productOptionDao.deleteOptionItems(no)) {
			return false;
		}
		if(!productDao.deleteImages(no)) {
			return false;
		}
		if(!productDao.updateDelStatus(no)) {
			return false;
		}
		return true;
	}

	

}
