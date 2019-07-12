package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.vo.MemberCheckDuplicateVo;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.cafe24.shoppingmall.vo.MemberLoginVo;

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
	private MemberService memberService;

	/**
	 * 회원가입
	 * 
	 * @param memberVo 회원가입 할 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 회원가입에 성공한 회원 정보
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@Valid @RequestBody MemberVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		Object result = memberService.join(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	/**
	 * 유저네임(=아이디) 중복검사
	 * 
	 * @param memberVo 중복검사 할 최소한의 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 중복 검사 결과 (unique / duplicated)
	 */
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkUsername(@Valid @RequestBody MemberCheckDuplicateVo memberVo,
			BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		Object result = memberService.checkUsername(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	/**
	 * 로그인
	 * 
	 * @param memberVo 로그인 할 최소한의 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 로그인에 성공한 회원의 정보
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> login(@Valid @RequestBody MemberLoginVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		Object result = memberService.login(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
}
