package com.cafe24.shoppingmall.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shoppingmall.validator.constraints.PhoneNumberPatternValidator;

/**
 * 휴대전화번호 유효성 검사 어노테이션 인터페이스
 * 
 * @author YDH
 *
 */
@Constraint(validatedBy = PhoneNumberPatternValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidPhoneNumber {
	String message() default "휴대전화번호 형식이 아닙니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}