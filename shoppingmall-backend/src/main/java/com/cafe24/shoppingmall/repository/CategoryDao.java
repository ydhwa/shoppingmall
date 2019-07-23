package com.cafe24.shoppingmall.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public Boolean insert(CategoryVo categoryVo) {
		return 1 == sqlSession.insert("category.insert", categoryVo);
	}

	public List<CategoryVo> getAll() {
		return sqlSession.selectList("category.getAll");
	}

	public List<CategoryVo> getAllParents() {
		return sqlSession.selectList("category.getAllParents");
	}

	public List<CategoryVo> getChildren(Long no) {
		return sqlSession.selectList("category.getChildren", no);
	}

	public CategoryVo getOne(Long no) {
		return sqlSession.selectOne("category.get", no);
	}

	public Boolean update(CategoryVo categoryVo) {
		return 1 == sqlSession.update("category.update", categoryVo);
	}

	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("category.delete", no);
	}

	// 상품에 카테고리 추가
	public boolean addProductCategories(List<List<CategoryVo>> categoryList) {
		// TODO Auto-generated method stub
		return false;
	}

}
