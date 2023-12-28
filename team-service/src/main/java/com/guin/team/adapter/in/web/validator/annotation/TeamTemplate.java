package com.guin.team.adapter.in.web.validator.annotation;

import com.guin.team.adapter.in.web.validator.TeamTemplateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TeamTemplateValidator.class)
public @interface TeamTemplate {

    String message() default "팀 템플릿 값이 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
