package com.cafe24.shoppingmall.validator.constraints;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shoppingmall.validator.ValidPassword;

public class PasswordPatternValidator implements ConstraintValidator<ValidPassword, String> {
	private Pattern pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}");

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.length() > 0 && !"".contentEquals(value) && pattern.matcher(value).matches();
	}
}