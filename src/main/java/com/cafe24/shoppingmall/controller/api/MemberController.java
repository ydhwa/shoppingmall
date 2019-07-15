package com.cafe24.shoppingmall.controller.api;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.validator.MemberValidator;
import com.cafe24.shoppingmall.validator.groups.MemberGroups;
import com.cafe24.shoppingmall.vo.MemberVo;

/**
 * 고객이 사용하는 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("memberAPIController")
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MemberService memberService;

	/**
	 * 회원가입
	 * 
	 * @param memberVo      회원가입 할 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 회원가입에 성공한 회원 정보
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@Valid @RequestBody MemberVo memberVo, BindingResult bindingResult) {
//		// Validation check
//		if (bindingResult.hasErrors()) {
//			List<ObjectError> errorList = bindingResult.getAllErrors();
//			for (ObjectError error : errorList) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
//			}
//		}
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validate(memberVo, MemberGroups.Login.class);

		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				String message = validatorResult.getMessage();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(message));
			}
		}

		Object result = memberService.join(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	/**
	 * 유저네임(=아이디) 중복검사
	 * 
	 * @param username 중복검사 할 아이디
	 * @return 중복검사 결과 (unique / duplicated)
	 */
	@RequestMapping(value = "/username/{username:[a-zA-Z0-9_]{4,12}}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkUsername(@PathVariable("username") String username) {
		Object result = memberService.checkUsername(username);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	/**
	 * 로그인
	 * 
	 * @param memberVo      로그인 할 최소한의 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 로그인에 성공한 회원의 정보
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> login(@Validated(MemberGroups.Login.class) @RequestBody MemberVo memberVo, BindingResult bindingResult) {
		// Validation check
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validate(memberVo, MemberGroups.Login.class);
//
//		if (validatorResults.isEmpty() == false) {
//			for (ConstraintViolation<MemberVo> validatorResult: validatorResults) {
//				String message = validatorResult.getMessage();
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(message));
//			}
//		}
//		SpringValidatorAdapter validatorAdapter = new SpringValidatorAdapter(validator);
//	    validatorAdapter.validate(memberVo, errors, MemberGroups.Login.class);
//
//	    if(errors.hasErrors()) {
//	    	for (ObjectError error : errors.getFieldErrors()) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
//			}
//	    }
	    
//		if (bindingResult.hasErrors()) {
//			List<FieldError> errorList = bindingResult.getFieldErrors();
//			for (ObjectError error : errorList) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(messageSource.getMessage(error, Locale.getDefault())));
//			}
//		}
		
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(memberVo, bindingResult);
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(messageSource.getMessage(error, Locale.getDefault())));
			}
		}

		Object result = memberService.login(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
}
