package com.guin.team.adapter.in.web.validator;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.validator.annotation.TeamRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class TeamRoleValidator implements ConstraintValidator<TeamRole, List<TeamCreateRequest.TeamCreateRoleRequest>> {

    private static final int MAX_NAME_LENGTH = 30;

    @Override
    public boolean isValid(List<TeamCreateRequest.TeamCreateRoleRequest> teamCreateRoleRequests, ConstraintValidatorContext constraintValidatorContext) {
        if(teamCreateRoleRequests == null) {
            return true;
        }

        if(maxLengthValidate(teamCreateRoleRequests)) {
            return true;
        }

        return false;
    }

    private boolean maxLengthValidate(final List<TeamCreateRequest.TeamCreateRoleRequest> teamCreateRoleRequests) {
        return teamCreateRoleRequests.stream()
                .noneMatch(teamCreateRoleRequest -> teamCreateRoleRequest.name().length() > MAX_NAME_LENGTH);
    }

}
