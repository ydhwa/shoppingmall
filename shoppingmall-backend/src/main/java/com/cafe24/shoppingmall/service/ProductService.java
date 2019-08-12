package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.repository.BucketItemDao;
import com.cafe24.shoppingmall.repository.CategoryDao;
import com.cafe24.shoppingmall.repository.OrdersDao;
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
	@Autowired
	private OrdersDao ordersDao;


	// 상품 등록
	@Transactional
	public Boolean registProduct(ProductVo product, List<ProductOptionVo> productOptionList, List<ProductOptionItemVo> productOptionItemList, List<CategoryVo> categoryList, List<ProductImageVo> productImageList) {
		// 1. 상품을 등록한다.
		if(!productDao.insert(product)) {
			return false;
		}
		// 2. 옵션 정보를 등록한다.
		if(!productOptionDao.insertOptions(productOptionList)) {
			return false;
		}
		// 3. 2의 조합으로 생성된 옵션 품목들을 등록한다.
		if(!productOptionDao.insertOptionItems(productOptionItemList)) {
			return false; 
		}
		
		// 나머지 항목은 없을 수도 있는 item들이므로, 우선 존재 여부를 체크한 후 존재하는 경우에만 insert 작업을 진행한다.
		// 4. 상품에 지정된 카테고리 정보가 있다면 상품 카테고리 정보를 등록한다. 
		if(categoryList != null && !categoryDao.addProductCategories(categoryList)) {
			return false;
		}
		// 5. 상품 이미지 정보가 있다면 상품 이미지 정보를 등록한다.
		if(productImageList != null && !productDao.insertImages(productImageList)) {
			return false;
		}

		return true;
	}

	// 상품 검색 결과 조회(관리자용)
	public List<ProductSummaryDto> searchProductsToAdmin(HashMap<String, String> paramMap) {
		return productDao.getSearchListToAdmin(paramMap);
	}

	// 상품 상세 조회(관리자용)
	public ProductDetailsDto showProductToAdmin(Long no) {
		ProductDetailsDto product = productDao.getOneToAdmin(no);
		product.setOptionList(productOptionDao.getListByProductNo(no));
		product.setOptionItemList(productOptionDao.getItemListByProductNo(no));
		product.setCategoryList(categoryDao.getListByProductNo(no));
		product.setProductImageList(productDao.getImageListByNo(no));

		return product;
	}

	// 상품 수정
	@Transactional
	public Boolean modifyProductToAdmin(ProductVo product, List<ProductOptionVo> productOptionList, List<ProductOptionItemVo> productOptionItemList, List<CategoryVo> categoryList, List<ProductImageVo> productImageList) {
		// 상품 정보 수정
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

		// 품목 삭제 전, 장바구니에 담겨 있던 품목들은 전부 삭제해야 한다.
		if(!bucketItemDao.deleteItemsByProductNo(product.getNo())) {
			return false;
		}
		// 주문 내역에 담겨 있는 품목번호는 전부 null처리시킨다.
		if(!ordersDao.updateProductOptionItemNoToNull(product.getNo())) {
			return false;
		}
		// 기존 옵션 품목 삭제
		if(!productOptionDao.deleteOptionItems(product.getNo())) {
			return false;
		}
		// 새로운 옵션 품목 추가
		if(!productOptionDao.insertOptionItems(productOptionItemList)) {
			return false;
		}

		// 나머지 항목은 없을 수도 있는 item들이므로, 우선 존재 여부를 체크한 후 존재하는 경우에만 삽입 작업을 진행한다.
		// 기존 카테고리 삭제 후 새 카테고리 추가
		if(!categoryDao.deleteProductCategories(product.getNo())) {
			return false;
		}
		if(categoryList != null && !categoryDao.addProductCategories(categoryList)) {
			return false;
		}
		// 기존 상품 이미지 삭제 후 새 상품 이미지 추가
		if(!productDao.deleteImages(product.getNo())) {
			return false;
		}
		if(productImageList != null && !productDao.insertImages(productImageList)) {
			return false;
		}

		return true;
	}

	// 상품 삭제
	@Transactional
	public Boolean deleteProductToAdmin(Long no) {
		// 상품 정보 제외하고 나머지 연관된 정보는 전부 지워버린다.
		// 카테고리, 장바구니, 옵션, 품목, 상품 이미지
		if(!categoryDao.deleteProductCategories(no)) {
			return false;
		}
		if(!productDao.deleteImages(no)) {
			return false;
		}
		if(!categoryDao.deleteProductCategories(no)) {
			return false;
		}
		if(!bucketItemDao.deleteItemsByProductNo(no)) {
			return false;
		}
		// 주문 내역에 담겨 있는 품목번호는 전부 null처리시킨다.
		if(!ordersDao.updateProductOptionItemNoToNull(no)) {
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
		// 상품 삭제 상태를 'Y'로 변경한다.
		if(!productDao.updateDelStatus(no)) {
			return false;
		}
		return true;
	}

	// 상품 검색 결과 조회(고객용)
	public List<ProductSummaryDto> searchProducts(HashMap<String, String> paramMap) {
		return productDao.getSearchList(paramMap);
	}

	// 상품 상세 조회(고객용)
	public ProductDetailsDto showProduct(Long no) {
		ProductDetailsDto product = productDao.getOne(no);
		product.setOptionList(productOptionDao.getListByProductNo(no));
		product.setOptionItemList(productOptionDao.getItemListByProductNo(no));
		product.setCategoryList(categoryDao.getListByProductNo(no));
		product.setProductImageList(productDao.getImageListByNo(no));

		return product;
	}

	public ProductOptionItemVo showProductItem(Map<String, String> paramMap) {
		return productDao.getOneItem(paramMap);
	}
}
