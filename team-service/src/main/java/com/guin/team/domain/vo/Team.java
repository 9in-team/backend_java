package com.guin.team.domain.vo;

import com.guin.team.domain.constant.SubjectType;

public record Team(
    Long id,
    Long leaderId,
    String subject,
    String content,
    SubjectType subjectType,
    String openChatUrl
) { }
