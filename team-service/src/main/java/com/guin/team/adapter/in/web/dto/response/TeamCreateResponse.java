package com.guin.team.adapter.in.web.dto.response;

import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;

import java.util.List;

public record TeamCreateResponse(
        Long teamId,
        String openChatUrl,
        String content,
        String subject,
        SubjectType subjectType,
        List<TeamTemplateDetail> teamTemplates,
        List<String> hashTags,
        List<TeamRoleDetail> teamRoleDetail
) {

    public record TeamTemplateDetail(
        TemplateType type,
        String question,
        String option
    ) { }

    public record TeamRoleDetail(
            String name,
            int requiredCount,
            int hiredCount
    ) { }

}
