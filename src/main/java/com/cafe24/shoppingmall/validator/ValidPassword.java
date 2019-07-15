package com.cafe24.shoppingmall.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shoppingmall.validator.constraints.PasswordPatternValidator;

@Constraint(validatedBy = PasswordPatternValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidPassword {
	String message() default "{PasswordPattern.memberVo.password}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
