package com.guin.team.application.service.dto;

import com.guin.team.domain.constant.SubjectType;

import java.util.List;

public record TeamCommand(
        String subject,
        String content,
        SubjectType subjectType,
        String openChatUrl,
        List<String> hashTags
) { }
