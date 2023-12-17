package com.guin.team.application.dto;

import com.guin.team.domain.constant.SubjectType;

public record TeamCommand(
        String subject,
        String content,
        SubjectType subjectType,
        String openChatUrl
) { }
