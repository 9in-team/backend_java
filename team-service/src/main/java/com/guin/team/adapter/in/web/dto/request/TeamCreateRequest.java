package com.guin.team.adapter.in.web.dto.request;

import com.guin.team.adapter.in.web.validator.annotation.HashTag;
import com.guin.team.adapter.in.web.validator.annotation.TeamRole;
import com.guin.team.adapter.in.web.validator.annotation.TeamTemplate;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TeamCreateRequest(
        @NotBlank(message = "제목은 빈값을 넣을 수 없습니다.")
        String subject,
        @NotBlank(message = "내용은 빈 값을 넣을 수 없습니다.")
        String content,

        @Size(max = 500, message = "채팅 주소는 500자를 넘길 수 없습니다.")
        String openChatUrl,
        @NotNull(message = "주제타입은 빈 값을 넣을 수 없습니다.")
        SubjectType subjectType,

        @TeamTemplate(message = "질문은 250자를 넘길 수 없습니다.")
        List<TeamCreateTemplateRequest> teamTemplates,

        @HashTag(message = "해시태그 최대 5개 10글자를 넘을 수 없습니다.")
        List<String> hashTags,

        @TeamRole(message = "팀 모집군 이름은 30자를 넘길 수 없습니다.")
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
