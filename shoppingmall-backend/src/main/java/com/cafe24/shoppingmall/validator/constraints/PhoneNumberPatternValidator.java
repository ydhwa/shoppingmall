package com.cafe24.shoppingmall.validator.constraints;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shoppingmall.validator.ValidPhoneNumber;

/**
 * 휴대전화번호 유효성 검사 Validator
 * 
 * @author YDH
 *
 */
public class PhoneNumberPatternValidator implements ConstraintValidator<ValidPhoneNumber, String> {
	private Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");

	@Override
	public void initialize(ValidPhoneNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.length() > 0 && !"".contentEquals(value) && pattern.matcher(value).matches();
	}
}