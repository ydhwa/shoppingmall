package com.cafe24.shoppingmall.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.vo.CategoryVo;

/**
 * 카테고리에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("categoryAPIController")
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 카테고리를 등록한다.
	 * 
	 * @param categoryVo 등록할 카테고리
	 * @return 등록된 카테고리 정보
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> registCategory(@RequestBody CategoryVo categoryVo) {
		CategoryVo registCategoryVo = categoryService.insert(categoryVo);
		
		if(categoryVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("카테고리 등록에 실패했습니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registCategoryVo));
		}
	}
	
	// 전부 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllCategories() {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 최상위 카테고리 조회
	@RequestMapping(value="/parents", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllTopLevelCategories() {
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 하위 카테고리 조회
	@RequestMapping(value="/{no}/children", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showChildrenCategories(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 특정 카테고리 조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 수정
	@RequestMapping(value="/{no}", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return null;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
}
