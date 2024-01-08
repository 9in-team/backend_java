package com.guin.team.adapter.in.web.validator;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.validator.annotation.TeamTemplate;
import com.guin.team.domain.constant.TemplateType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import java.util.List;
import java.util.Objects;

public class TeamTemplateValidator implements ConstraintValidator<TeamTemplate, List<TeamCreateRequest.TeamCreateTemplateRequest>> {

    private static final int MAX_QUESTION_LENGTH = 250;
    private static final String ENOUGH_CHECKBOX_OPTION = "템플릿이 체크박스일경우에는 옵션이 필수 입니다.";

    @Override
    public boolean isValid(List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests, ConstraintValidatorContext constraintValidatorContext) {
        if (teamCreateTemplateRequests == null) {
            return true;
        }

        if (isMaxLengthQuestionValidate(teamCreateTemplateRequests)) {
            return false;
        }

        if (isCheckboxTemplateOptionValidate(teamCreateTemplateRequests)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ENOUGH_CHECKBOX_OPTION)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isMaxLengthQuestionValidate(final List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests) {
        return teamCreateTemplateRequests.stream()
                .anyMatch(teamCreateTemplateRequest -> teamCreateTemplateRequest.question().length() > MAX_QUESTION_LENGTH);
    }

    private boolean isCheckboxTemplateOptionValidate(final List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests) {
        return teamCreateTemplateRequests.stream()
                .anyMatch(teamCreateTemplateRequest -> teamCreateTemplateRequest.type() == TemplateType.CHECKBOX
                                                    && teamCreateTemplateRequest.option() == null);
    }

}
