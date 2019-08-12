package com.cafe24.shoppingmall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductImageDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductVo;

/**
 * 상품에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class ProductDao {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 상품 등록
	public boolean insert(ProductVo productVo) {
		int result = sqlSession.insert("product.insert", productVo);
		return 1 == result;
	}
	// 상품 이미지 등록
	public boolean insertImages(List<ProductImageVo> productImageList) {
		int result = 0;
		for(ProductImageVo productImageVo: productImageList) {
			result += sqlSession.insert("product.insertImage", productImageVo);
		}
		return productImageList.size() == result;
	}

	// 관리자가 상품 정보 검색 (더 많은 정보를 열람할 수 있다.)
	public List<ProductSummaryDto> getSearchListToAdmin(HashMap<String, String> paramMap) {
		return sqlSession.selectList("product.searchToAdmin", paramMap);
	}

	// 관리자가 상품 번호로 조회 (더 많은 정보를 열람할 수 있다.) 
	public ProductDetailsDto getOneToAdmin(Long no) {
		return sqlSession.selectOne("product.getToAdmin", no);
	}
	
	// 상품 이미지 번호로 조회
	public List<ProductImageDto> getImageListByNo(Long no) {
		return sqlSession.selectList("product.getImageListByNo", no);
	}

	// 상품 정보 수정
	public boolean update(ProductVo product) {
		return 1 == sqlSession.update("product.updateToAdmin", product);
	} 

	// 상품 이미지 삭제
	public boolean deleteImages(Long no) {
		return 0 <= sqlSession.delete("product.deleteImages", no);
	}

	// 상품 삭제
	// 정확히는 삭제 상태만 활성화시킨다.
	public boolean updateDelStatus(Long no) {
		return 1 == sqlSession.update("product.updateDelStatus", no);
	}

	// 고객이 상품 정보 검색
	public List<ProductSummaryDto> getSearchList(HashMap<String, String> paramMap) {
		return sqlSession.selectList("product.search", paramMap);
	}

	// 고객이 상품 번호로 조회
	public ProductDetailsDto getOne(Long no) {
		return sqlSession.selectOne("product.get", no);
	}
	
	// 상품 번호와 value key들로 조회
	public ProductOptionItemVo getOneItem(Map<String, String> paramMap) {
		return sqlSession.selectOne("productoption.getItem", paramMap);
	}
}
