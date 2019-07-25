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
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.CategoryVo;
import com.cafe24.shoppingmall.vo.ProductImageVo;
import com.cafe24.shoppingmall.vo.ProductOptionItemVo;
import com.cafe24.shoppingmall.vo.ProductOptionVo;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 상품에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("ProductAPIController")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 상품을 등록한다.
	 * 
	 * @param productVo 등록할 상품
	 * @return 등록된 상품정보
	 */
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
		
		if("N".equals(product.getOptionAvailable())) {	// 옵션을 사용하지 않는 경우
			productOptionList.add(new ProductOptionVo("없음", null));
			productOptionItemList.add(new ProductOptionItemVo(null, null, "없음", 0, product.getAvailability(), product.getManageStatus(), product.getStockQuantity()));
		} else {	// 옵션을 사용하는 경우
			productOptionList = mapper.convertValue(productMap.get("productOptionList"), new TypeReference<List<ProductOptionVo>>() {});
			productOptionItemList = mapper.convertValue(productMap.get("productOptionItemList"), new TypeReference<List<ProductOptionItemVo>>() {});
		}
		
		boolean registResult = productService.registProduct(product, productOptionList, productOptionItemList, categoryList, productImageList);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
	}

	// 검색결과 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductsSearchResult(
			@RequestParam(value="no") Long no,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="categoryno") Long categoryNo,
			@RequestParam(value="displaystatus") String displayStatus,
			@RequestParam(value="offset", required=true) Integer offset,
			@RequestParam(value="limit", required=true) Integer limit) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("code", code);
		map.put("name", name);
		map.put("categoryno", categoryNo);
		map.put("displaystatus", displayStatus);
		map.put("offset", offset);
		map.put("limit", limit);

		List<ProductVo> productVoList = productService.searchProducts(map);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVoList));
	}

	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showProductDetails(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return null;
		}

		ProductVo productVo = productService.getProduct(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
	}

	// 수정
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyProduct(@RequestBody ProductVo productVo) {
		if (productVo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 수정에 실패했습니다."));
		}

		Boolean modifyResult = productService.modifyProduct(productVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyResult));
	}

	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteProduct(@PathVariable Optional<Long> no) {
		// path variable check
		if (!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("상품 정보 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = productService.deleteProduct(no.get());

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
}
