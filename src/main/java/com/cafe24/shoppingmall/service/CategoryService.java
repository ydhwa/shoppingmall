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

	public Boolean regist(CategoryVo categoryVo) {
		return null;
	}

	public List<CategoryVo> getAll() {
		return null;
	}

	public List<CategoryVo> getAllParents() {
		return null;
	}

	public List<CategoryVo> getChildren(Long long1) {
		return null;
	}

	public CategoryVo getOne(Long long1) {
		return null;
	}

	public Boolean modify(Long long1) {
		return null;
	}

	public Boolean delete(Long long1) {
		return null;
	}
}
