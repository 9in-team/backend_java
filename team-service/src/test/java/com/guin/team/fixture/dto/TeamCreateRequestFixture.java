package com.guin.team.fixture.dto;

import com.guin.team.fixture.Fixture;
import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.domain.constant.SubjectType;

import java.util.Collections;
import java.util.UUID;

public class TeamCreateRequestFixture extends Fixture {

    public static TeamCreateRequest create(final String subject) {
        String randomUUID = UUID.randomUUID().toString();

        return new TeamCreateRequest(
                subject,
                "content " + randomUUID,
                randomUUID,
                SubjectType.PROJECT,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

}
