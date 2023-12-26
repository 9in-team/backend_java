package com.guin.team.fixture.domain;

import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.vo.Team;
import com.guin.team.fixture.Fixture;

public class TeamFixture extends Fixture {

    public static Team create(final String subject,
                              final String content,
                              final SubjectType subjectType,
                              final String openChatUrl) {
        return fixtureMonkey.giveMeBuilder(Team.class)
                .set("id", 1L)
                .set("subject", subject)
                .set("content", content)
                .set("subjectType", subjectType)
                .set("openChatUrl", openChatUrl)
                .sample();
    }

}
