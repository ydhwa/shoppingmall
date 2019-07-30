package com.cafe24.shoppingmall.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shoppingmall.validator.constraints.UsernamePatternValidator;

/**
 * 아이디 유효성 검사 어노테이션 인터페이스
 * 
 * @author YDH
 *
 */
@Constraint(validatedBy = UsernamePatternValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidUsername {
	String message() default "아이디는 4자 이상 12자 이하의 알파벳, 숫자, _(언더바)를 이용하여 작성해야 합니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}