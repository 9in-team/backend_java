package com.guin.team.application.port.in.command;

import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;

import java.util.List;

public record TeamCommand(
        String subject,
        String content,
        SubjectType subjectType,
        String openChatUrl,
        List<String> hashTags,
        List<TeamTemplate> teamTemplates,
        List<TeamRole> teamRoles
) {

    public record TeamTemplate(
            TemplateType type,
            String question,
            String option
    ) { }

    public record TeamRole(
            String name,
            int requiredCount
    ) { }

}
