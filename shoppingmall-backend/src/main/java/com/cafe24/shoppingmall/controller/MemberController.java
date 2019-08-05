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
import com.cafe24.shoppingmall.dto.MemberDto;
import com.cafe24.shoppingmall.dto.ProductDetailsDto;
import com.cafe24.shoppingmall.service.MemberService;
import com.cafe24.shoppingmall.validator.constraints.UsernamePatternValidator;
import com.cafe24.shoppingmall.validator.groups.MemberGroups;
import com.cafe24.shoppingmall.vo.MemberVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 회원 정보에 대한 API 컨트롤러
 * 
 * @author YDH
 *
 */
@RestController("memberAPIController")
@RequestMapping("/api/members")
@Api(value="/api/members", description="회원 컨트롤러", consumes="application/json")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@ApiOperation(value="회원가입")
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberVo", value="가입할 회원 정보", dataType="MemberVo")
	})
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

	@ApiOperation(value="아이디 중복 검사")
	@ApiImplicitParams({
		@ApiImplicitParam(name="username", value="중복검사할 아이디", dataType="string", paramType="query")
	})
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

	@ApiOperation(value="로그인")
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberVo", value="로그인을 시도할 회원 정보", dataType="MemberVo")
	})
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
	
	@ApiOperation(value="회원 상세조회", response=ProductDetailsDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="조회할 회원 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원 상세조회에 실패했습니다."));
		}
		
		MemberVo memberVo = memberService.showMemberDetails(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	
	@ApiOperation(value="회원 정보 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberVo", value="수정할 회원 정보", dataType="MemberVo", paramType="body")
	})
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
	
	@ApiOperation(value="회원 정보 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no", value="삭제할 회원 번호", dataType="Long", paramType="path")
	})
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteMember(@PathVariable("no") Optional<Long> no) {
		// path variable check
		if(!no.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원 삭제에 실패했습니다."));
		}
		
		Boolean deleteResult = memberService.delete(no.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleteResult));
	}
	
	@ApiOperation(value="아이디로 간단한 회원 정보 꺼내오기(Spring Security)")
	@ApiImplicitParams({
		@ApiImplicitParam(name="username", value="조회할 회원 아이디", dataType="String", paramType="path")
	})
	@RequestMapping(value="/security/{username}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> showMemberByUsername(@PathVariable("username") Optional<String> username) {
		// path variable check
		if(!username.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.failure("회원 정보를 얻어오는 데 실패했습니다."));
		}
		
		MemberDto memberDto = memberService.getByUsername(username.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberDto));
	}
}
