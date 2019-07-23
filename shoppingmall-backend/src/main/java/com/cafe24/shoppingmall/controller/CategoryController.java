package com.cafe24.shoppingmall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> registCategory(@RequestBody CategoryVo categoryVo) {
		if (categoryVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("카테고리 등록에 실패했습니다."));
		}
		
		Boolean registResult = categoryService.registCategory(categoryVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	// 전부 조회
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllCategories() {
		List<CategoryVo> categoryVoList = categoryService.getAllCategories();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryVoList));
	}

	// 최상위 카테고리들 조회
	@RequestMapping(value = "/parents", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllTopLevelCategories() {
		List<CategoryVo> parentCategoryVoList = categoryService.getAllTopLevelCategories();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(parentCategoryVoList));
	}

	// 하위 카테고리까지 포함하여 조회
	@RequestMapping(value = "/{no}/children", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showChildrenCategories(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("올바른 데이터가 아닙니다."));
		}

		List<CategoryVo> childCategoryVoList = categoryService.getChildrenOfCategory(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(childCategoryVoList));
	}

	// 특정 카테고리 조회
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> showCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("올바른 데이터가 아닙니다."));
		}

		CategoryVo categoryVo = categoryService.getCategory(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryVo));
	}

	// 수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyCategory(@RequestBody CategoryVo categoryVo) {
		if (categoryVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("카테고리 수정에 실패했습니다."));
		}
		
		Boolean modifyResult = categoryService.modifyCategory(categoryVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyResult));
	}

	// 삭제
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("올바른 데이터가 아닙니다."));
		}

		Boolean deleteResult = categoryService.deleteCategory(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
}
