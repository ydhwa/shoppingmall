package com.cafe24.shoppingmall.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cafe24.shoppingmall.vo.MemberVo;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class arg0) {
		return MemberVo.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
