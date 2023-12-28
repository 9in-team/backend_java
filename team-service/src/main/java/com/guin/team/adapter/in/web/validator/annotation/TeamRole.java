package com.guin.team.adapter.in.web.validator.annotation;

import com.guin.team.adapter.in.web.validator.HashTagValidator;
import com.guin.team.adapter.in.web.validator.TeamRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TeamRoleValidator.class)
public @interface TeamRole {
    String message() default "팀 구성이 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
