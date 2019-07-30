package com.cafe24.shoppingmall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.dto.ProductSummaryDto;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminProductAPIController")
@RequestMapping("/api/admin/products")
@Api(value="/api/admin/products", description="관리자 상품 컨트롤러", consumes="application/json")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value="상품 등록")
	@ApiImplicitParams({
		@ApiImplicitParam(name="productMap", value="등록할 상품 정보", dataType="Map")
	})
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> registProduct(@RequestBody Map<String, Object> productMap) {
		if (productMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}

		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		ProductVo product = mapper.convertValue(productMap.get("product"), ProductVo.class);
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		List<CategoryVo> categoryList = mapper.convertValue(productMap.get("categoryList"), new TypeReference<List<CategoryVo>>() {});
		List<ProductImageVo> productImageList = mapper.convertValue(productMap.get("productImageList"), new TypeReference<List<ProductImageVo>>() {});
		
		// 필수 사항에 대하여 체크한다.
		if(product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 등록에 실패했습니다."));
		}
		
		if("N".equals(product.getOptionAvailable())) {	// 옵션을 사용하지 않는 경우
			productOptionList.add(new ProductOptionVo("없음", null));
			productOptionItemList.add(new ProductOptionItemVo(null, null, "없음", 0, product.getAvailability(), product.getManageStatus(), product.getStockQuantity()));
		} else {	// 옵션을 사용하는 경우
			productOptionList = mapper.convertValue(productMap.get("productOptionList"), new TypeReference<List<ProductOptionVo>>() {});
			productOptionItemList = mapper.convertValue(productMap.get("productOptionItemList"), new TypeReference<List<ProductOptionItemVo>>() {});
		}
		
		Boolean registResult = productService.registProduct(product, productOptionList, productOptionItemList, categoryList, productImageList);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	// swagger로는 테스트 할 수 없다.
	@ApiOperation(value="상품 검색목록 조회", response=ProductSummaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="paramMap", value="검색조건", dataType="string", paramType="query")
	})
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(@RequestParam HashMap<String, String> paramMap) {
		if (paramMap == null || !paramMap.containsKey("offset") || !paramMap.containsKey("limit")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 검색에 실패했습니다."));
		}

		List<ProductSummaryDto> productList = productService.searchProductsToAdmin(paramMap);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
	}

	@ApiOperation(value="상품 상세조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 상품 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 상세 조회에 실패했습니다."));
		}

		ProductDetailsDto product = productService.showProductToAdmin(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product));
	}

	@ApiOperation(value="상품 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name="productMap", value="수정할 상품 정보", dataType="Map", paramType="body")
	})
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyProduct(@RequestBody Map<String, Object> productMap) {
		if (productMap == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 수정에 실패했습니다."));
		}

		// 변환작업
		ObjectMapper mapper = new ObjectMapper();
		ProductVo product = mapper.convertValue(productMap.get("product"), ProductVo.class);
		List<ProductOptionVo> productOptionList = new ArrayList<>();
		List<ProductOptionItemVo> productOptionItemList = new ArrayList<>();
		List<CategoryVo> categoryList = mapper.convertValue(productMap.get("categoryList"), new TypeReference<List<CategoryVo>>() {});
		List<ProductImageVo> productImageList = mapper.convertValue(productMap.get("productImageList"), new TypeReference<List<ProductImageVo>>() {});
		
		// 필수 사항에 대하여 체크한다.
		if(product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 수정에 실패했습니다."));
		}
		
		if("N".equals(product.getOptionAvailable())) {	// 옵션을 사용하지 않는 경우
			productOptionList.add(new ProductOptionVo("없음", null));
			productOptionItemList.add(new ProductOptionItemVo(null, null, "없음", 0, product.getAvailability(), product.getManageStatus(), product.getStockQuantity()));
		} else {	// 옵션을 사용하는 경우
			productOptionList = mapper.convertValue(productMap.get("productOptionList"), new TypeReference<List<ProductOptionVo>>() {});
			productOptionItemList = mapper.convertValue(productMap.get("productOptionItemList"), new TypeReference<List<ProductOptionItemVo>>() {});
		}
		
		Boolean modifyResult = productService.modifyProductToAdmin(product, productOptionList, productOptionItemList, categoryList, productImageList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyResult));
	}

	@ApiOperation(value="상품 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="삭제할 상품 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteProduct(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = productService.deleteProductToAdmin(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
}
