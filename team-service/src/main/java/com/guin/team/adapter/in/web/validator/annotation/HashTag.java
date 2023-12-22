package com.guin.team.adapter.in.web.validator.annotation;

import com.guin.team.adapter.in.web.validator.HashTagValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HashTagValidator.class)
public @interface HashTag {

    String message() default "해시태그 값이 잘못되었습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
