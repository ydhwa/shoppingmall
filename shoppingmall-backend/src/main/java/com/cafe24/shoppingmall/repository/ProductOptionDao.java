package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.OptionDto;
import com.cafe24.shoppingmall.dto.OptionItemDto;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionValueVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;

/**
 * 상품 옵션에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class ProductOptionDao {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 옵션 추가 
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

	// 옵션 품목 추가
	public boolean insertOptionItems(List<ProductOptionItemVo> productOptionItemList) {
		int result = 0;
		for(ProductOptionItemVo productOptionItemVo: productOptionItemList) {
			result += sqlSession.insert("productoption.insertItem", productOptionItemVo);
		}
		return productOptionItemList.size() == result;
	}

	// 상품 번호에 대한 옵션들 조회
	public List<OptionDto> getListByProductNo(Long no) {
		List<OptionDto> productOptionList =  sqlSession.selectList("productoption.getListByProductNo", no);
		for(OptionDto option: productOptionList) {
			option.setProductOptionValueList(sqlSession.selectList("productoption.getValueListByOptionNameNo", option.getNo()));
		}
		
		return productOptionList;
	}
	
	// 상품 번호에 대한 옵션 품목들 조회
	public List<OptionItemDto> getItemListByProductNo(Long no) {
		return sqlSession.selectList("productoption.getItemListByProductNo", no);
	}

	// 상품 번호로 옵션 전부 삭제
	// 상품 수정 및 상품 삭제 시 사용됨
	public boolean deleteOptions(Long productNo) {
		return 0 < sqlSession.delete("productoption.deleteOptions", productNo);
	}
	// 상품 번호로 옵션 품목 전부 삭제
	// 상품 수정 및 상품 삭제 시 사용됨
	public boolean deleteOptionItems(Long productNo) {
		return 0 < sqlSession.delete("productoption.deleteOptionItems", productNo);
	}
}
