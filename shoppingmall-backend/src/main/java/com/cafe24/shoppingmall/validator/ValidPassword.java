package com.cafe24.shoppingmall.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shoppingmall.validator.constraints.PasswordPatternValidator;

/**
 * 비밀번호 유효성 검사 어노테이션 인터페이스
 * 
 * @author YDH
 *
 */
@Constraint(validatedBy = PasswordPatternValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidPassword {
	String message() default "비밀번호는 8자 이상 20자 이하의 알파벳, 숫자, 특수문자를 조합하여 작성해야 합니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}