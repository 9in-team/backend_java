package com.guin.team.fixture.dto;

import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.domain.constant.SubjectType;

import java.util.Collections;
import java.util.List;

public class TeamCreateResponseFixture {

    public static TeamCreateResponse create(final Long teamId,
                                            final String openChatUrl,
                                            final String content,
                                            final String subject,
                                            final SubjectType subjectType) {
        return new TeamCreateResponse(
                teamId,
                openChatUrl,
                content,
                subject,
                subjectType,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public static TeamCreateResponse create(final Long teamId,
                                            final String openChatUrl,
                                            final String content,
                                            final String subject,
                                            final SubjectType subjectType,
                                            final List<String> hashTags) {
        return new TeamCreateResponse(
                teamId,
                openChatUrl,
                content,
                subject,
                subjectType,
                Collections.emptyList(),
                hashTags,
                Collections.emptyList()
        );
    }

}
