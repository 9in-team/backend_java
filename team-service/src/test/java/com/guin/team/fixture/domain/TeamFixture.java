package com.guin.team.fixture.domain;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.vo.Team;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;
import com.guin.team.fixture.Fixture;

import java.util.List;

public class TeamFixture {

    public static Team create(final String subject,
                              final String content,
                              final SubjectType subjectType,
                              final String openChatUrl,
                              final List<String> hashTags,
                              final List<TeamRole> teamRoles,
                              final List<TeamTemplate> teamTemplates) {
        return new Team(1L,
                1L,
                subject,
                content,
                subjectType,
                openChatUrl,
                hashTags,
                teamRoles,
                teamTemplates
        );
    }

}
