package com.guin.team.fixture.command;

import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;
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
                .set("teamTemplates", List.of(createCheckBox()))
                .set("teamRoles", List.of(createTeamRole("백엔드 개발자")))
                .sample();
    }

    public static TeamCommand.TeamTemplate createCheckBox() {
        return new TeamCommand.TeamTemplate(
            TemplateType.CHECKBOX,
                "열심히 하시겠습니까?",
                "네,아니오"
        );
    }

    public static TeamCommand.TeamRole createTeamRole(final String name) {
        return new TeamCommand.TeamRole(name, 1);
    }


    public static TeamCommand create(final String subject, final List<String> hashTags) {
        String randomUUID = UUID.randomUUID().toString();

        return fixtureMonkey.giveMeBuilder(TeamCommand.class)
                .set("subject", subject)
                .set("openChatUrl", randomUUID)
                .set("subjectType", SubjectType.PROJECT)
                .set("hashTags", hashTags)
                .set("content", "본문")
                .set("teamTemplates", List.of(createCheckBox()))
                .set("teamRoles", List.of(createTeamRole("백엔드 개발자")))
                .sample();
    }

}
