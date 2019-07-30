package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.CategoryDto;
import com.cafe24.shoppingmall.vo.CategoryVo;

/**
 * 카테고리에 대한 DAO
 * 
 * @author YDH
 *
 */
@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;

	// 카테고리 추가
	public Boolean insert(CategoryVo categoryVo) {
		return 1 == sqlSession.insert("category.insert", categoryVo);
	}

	// 카테고리 전부 조회
	public List<CategoryVo> getAll() {
		return sqlSession.selectList("category.getAll");
	}

	// 최상위 카테고리 전부 조회
	public List<CategoryVo> getAllParents() {
		return sqlSession.selectList("category.getAllParents");
	}

	// 지목된 카테고리의 자식 카테고리까지 전부 조회
	public List<CategoryVo> getChildren(Long no) {
		return sqlSession.selectList("category.getChildren", no);
	}

	// 특정 카테고리 조회
	public CategoryVo getOne(Long no) {
		return sqlSession.selectOne("category.get", no);
	}

	// 카테고리 수정
	public Boolean update(CategoryVo categoryVo) {
		return 1 == sqlSession.update("category.update", categoryVo);
	}

	// 카테고리 삭제
	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("category.delete", no);
	}

	// 상품에 카테고리 추가
	public boolean addProductCategories(List<CategoryVo> categoryList) {
		int result = 0;
		for(CategoryVo categoryVo: categoryList) {
			result += sqlSession.insert("category.insertProductCategory", categoryVo);
		}
		return categoryList.size() == result;
	}

	// 특정 상품의 카테고리 전부 조회
	public List<CategoryDto> getListByProductNo(Long no) {
		return sqlSession.selectList("category.getListByProductNo", no);
	}
	
	// 특정 상품의 카테고리 전부 삭제
	// 상품 수정/삭제 시 사용됨
	public boolean deleteProductCategories(Long no) {
		return 0 <= sqlSession.delete("category.deleteByProductNo", no);
	}

}
