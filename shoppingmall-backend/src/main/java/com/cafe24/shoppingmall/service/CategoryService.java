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

	public Boolean registCategory(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}

	public List<CategoryVo> getAllCategories() {
		return categoryDao.getAll();
	}

	public List<CategoryVo> getAllTopLevelCategories() {
		return categoryDao.getAllParents();
	}

	public List<CategoryVo> getChildrenOfCategory(Long no) {
		return categoryDao.getChildren(no);
	}

	public CategoryVo getCategory(Long no) {
		return categoryDao.getOne(no);
	}

	public Boolean modifyCategory(CategoryVo categoryVo) {
		return categoryDao.update(categoryVo);
	}

	public Boolean deleteCategory(Long no) {
		return categoryDao.delete(no);
	}
}
