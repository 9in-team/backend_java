package com.guin.team.domain.vo;

import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;

import java.util.List;

public record Team(
        Long id,
        Long leaderId,
        String subject,
        String content,
        SubjectType subjectType,
        String openChatUrl,
        List<String> hashTag,
        List<TeamRole> teamRoles,
        List<TeamTemplate> teamTemplates
) {
}
