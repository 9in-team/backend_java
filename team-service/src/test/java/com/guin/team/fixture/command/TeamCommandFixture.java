package com.guin.team.fixture.command;

import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.fixture.Fixture;

import java.util.List;
import java.util.UUID;

public class TeamCommandFixture extends Fixture {

    public static TeamCommand create(final String subject) {
        return fixtureMonkey.giveMeBuilder(TeamCommand.class)
                .set("subject", subject)
                .set("hashTags", List.of("스프링"))
                .set("content", "본문")
                .set("subjectType", SubjectType.PROJECT)
                .sample();
    }

    public static TeamCommand create(final String subject, final List<String> hashTags) {
        String randomUUID = UUID.randomUUID().toString();

        return fixtureMonkey.giveMeBuilder(TeamCommand.class)
                .set("subject", subject)
                .set("openChatUrl", randomUUID)
                .set("subjectType", SubjectType.PROJECT)
                .set("hashTags", hashTags)
                .set("content", "본문")
                .sample();
    }

}
