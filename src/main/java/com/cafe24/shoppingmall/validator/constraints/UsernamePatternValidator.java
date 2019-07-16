package com.cafe24.shoppingmall.validator.constraints;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shoppingmall.validator.ValidUsername;

public class UsernamePatternValidator implements ConstraintValidator<ValidUsername, String> {
	private Pattern pattern = Pattern.compile("[A-Za-z0-9_]{4,12}");

	@Override
	public void initialize(ValidUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.length() > 0 && !"".contentEquals(value) && pattern.matcher(value).matches();
	}
}