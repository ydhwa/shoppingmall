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
import com.cafe24.shoppingmall.vo.MemberJoinVo;
import com.cafe24.shoppingmall.vo.MemberLoginVo;

@RestController("memberAPIController")
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@Valid @RequestBody MemberJoinVo memberVo, BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		Object result = memberService.join(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkUsername(@Valid @RequestBody MemberCheckDuplicateVo memberVo,
			BindingResult bindingResult) {
		// Validation check
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.failure(error.getDefaultMessage()));
			}
		}
		Object result = memberService.checkUsername(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

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
