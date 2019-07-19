package com.cafe24.shoppingmall.service;

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

	/**
	 * 카테고리 추가
	 * 
	 * @param categoryVo
	 * @return
	 */
	public CategoryVo insert(CategoryVo categoryVo) {
		if(categoryDao.insert(categoryVo)) {
			return categoryVo;
		}
		else {
			return null;
		}
	}
}
