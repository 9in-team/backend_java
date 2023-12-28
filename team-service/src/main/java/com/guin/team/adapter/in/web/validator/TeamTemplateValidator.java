package com.guin.team.adapter.in.web.validator;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.validator.annotation.TeamTemplate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class TeamTemplateValidator implements ConstraintValidator<TeamTemplate, List<TeamCreateRequest.TeamCreateTemplateRequest>> {

    private static final int MAX_QUESTION_LENGTH = 250;

    @Override
    public boolean isValid(List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(teamCreateTemplateRequests)) {
            return true;
        }

        if (maxLengthQuestionValidate(teamCreateTemplateRequests)) {
            return true;
        }

        return false;
    }

    private boolean maxLengthQuestionValidate(final List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests) {
        return teamCreateTemplateRequests.stream()
                .noneMatch(teamCreateTemplateRequest -> teamCreateTemplateRequest.question().length() > MAX_QUESTION_LENGTH);
    }

}
