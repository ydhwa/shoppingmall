package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.CategoryDao;
import com.cafe24.shoppingmall.vo.CategoryVo;

/**
 * 카테고리에 대한 로직이 담긴 서비스.
 * 
 * @author YDH
 *
 */
@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	// 카테고리 등록
	public Boolean registCategory(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}

	// 모든 카테고리 조회
	public List<CategoryVo> getAllCategories() {
		return categoryDao.getAll();
	}

	// 최상위 카테고리 모두 조회
	public List<CategoryVo> getAllTopLevelCategories() {
		return categoryDao.getAllParents();
	}

	// 해당 카테고리의 하위 카테고리까지 모두 조회
	public List<CategoryVo> getChildrenOfCategory(Long no) {
		return categoryDao.getChildren(no);
	}

	// 특정 카테고리 조회
	public CategoryVo getCategory(Long no) {
		return categoryDao.getOne(no);
	}

	// 카테고리 수정
	public Boolean modifyCategory(CategoryVo categoryVo) {
		return categoryDao.update(categoryVo);
	}

	// 카테고리 삭제
	public Boolean deleteCategory(Long no) {
		return categoryDao.delete(no);
	}
}
