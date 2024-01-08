package com.guin.team.fixture.domain;

import com.guin.team.domain.vo.TeamRole;
import com.guin.team.fixture.Fixture;

public class TeamRoleFixture extends Fixture {

    public static TeamRole create(String name,
                                  int requiredCount,
                                  int hiredCount) {
        return fixtureMonkey.giveMeBuilder(TeamRole.class)
                .set("id", 1L)
                .set("name", name)
                .set("requiredCount", requiredCount)
                .set("hiredCount", hiredCount)
                .sample();
    }

}
