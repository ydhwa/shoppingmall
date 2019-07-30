package com.cafe24.shoppingmall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.vo.CategoryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("categoryAPIController")
@RequestMapping("/api/categories")
@Api(value="/api/categories", description="카테고리 컨트롤러", consumes="application/json")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value="카테고리 전부 조회")
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllCategories() {
		List<CategoryVo> categoryVoList = categoryService.getAllCategories();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryVoList));
	}

	@ApiOperation(value="최상위 카테고리 전부 조회")
	@RequestMapping(value="/parents", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showAllTopLevelCategories() {
		List<CategoryVo> parentCategoryVoList = categoryService.getAllTopLevelCategories();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(parentCategoryVoList));
	}

	@ApiOperation(value="하위 카테고리까지 포함하여 조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 카테고리 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}/children", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showChildrenCategories(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("올바른 데이터가 아닙니다."));
		}

		List<CategoryVo> childCategoryVoList = categoryService.getChildrenOfCategory(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(childCategoryVoList));
	}

	@ApiOperation(value="카테고리 조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 카테고리 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showCategory(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("올바른 데이터가 아닙니다."));
		}

		CategoryVo categoryVo = categoryService.getCategory(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryVo));
	}
}
