package com.cafe24.shoppingmall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.validator.constraints.UsernamePatternValidator;
import com.cafe24.shoppingmall.validator.groups.MemberGroups;
import com.cafe24.shoppingmall.vo.MemberVo;

/**
 * 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("memberAPIController")
@RequestMapping("/api/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 회원가입
	 * 
	 * @param memberVo 회원가입 할 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 회원가입 성공여부(true/false)
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@RequestBody @Validated(MemberGroups.Join.class) MemberVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		
		// DuplicateKeyException
		try {
			Boolean registResult = memberService.join(memberVo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(registResult));
		} catch(DuplicateKeyException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("이미 존재하는 아이디로는 회원가입을 할 수 없습니다."));
		}
	}

	/**
	 * 유저네임(=아이디) 중복검사
	 * 
	 * @param username 중복검사 할 아이디
	 * @return 중복여부(true(duplicated)/false(unique))
	 */
	@RequestMapping(value="/duplicate", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> checkUsername(@RequestParam("username") String username) {
		// Validation check
		UsernamePatternValidator validator = new UsernamePatternValidator();
		if(!validator.isValid(username, null)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("적절한 형식의 데이터가 아닙니다."));
		}
		
		Boolean result = memberService.checkUsernameDuplication(username);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	/**
	 * 로그인
	 * 
	 * @param memberVo 로그인 할 최소한의 정보가 담겨 있는 회원 정보 객체
	 * @param bindingResult 유효성 검사 결과
	 * @return 로그인에 성공한 회원의 정보
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> login(@RequestBody @Validated(MemberGroups.Login.class) MemberVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}

		MemberVo result = memberService.login(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}
	
	// 상세조회
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원 상세조회에 실패했습니다."));
		}
		
		MemberVo memberVo = memberService.showMemberDetails(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	
	// 수정
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyMember(@RequestBody @Validated(MemberGroups.Modify.class) MemberVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		
		Boolean modifyResult = memberService.modify(memberVo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(modifyResult));
	}
	
	// 삭제
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = memberService.delete(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
}
