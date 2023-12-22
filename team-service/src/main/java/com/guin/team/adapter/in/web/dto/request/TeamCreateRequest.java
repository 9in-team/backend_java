package com.guin.team.adapter.in.web.dto.request;

import com.guin.team.adapter.in.web.validator.annotation.HashTag;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TeamCreateRequest(
        @NotBlank(message = "제목은 빈값을 넣을 수 없습니다.")
        String subject,
        @NotBlank(message = "내용은 빈 값을 넣을 수 없습니다.")
        String content,
        String openChatUrl,
        @NotNull(message = "주제타입은 빈 값을 넣을 수 없습니다.")
        SubjectType subjectType,
        List<TeamCreateTemplateRequest> teamTemplates,

        @HashTag(message = "해시태그 최대 5개 10글자를 넘을 수 없습니다.")
        List<String> hashTags,
        List<TeamCreateRoleRequest> roles
) {

    public record TeamCreateTemplateRequest(
            TemplateType type,
            String question,
            String option
    ) {
    }

    public record TeamCreateRoleRequest(
            String name,
            int requiredCount
    ) {
    }

}
