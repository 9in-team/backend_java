package com.guin.team.fixture.domain;

import com.guin.team.domain.constant.TemplateType;
import com.guin.team.domain.vo.TeamTemplate;
import com.guin.team.fixture.Fixture;

import java.util.List;

public class TeamTemplateFixture extends Fixture {

    public static TeamTemplate create(TemplateType templateType,
                                      String question,
                                      List<String> optionNames) {
        return fixtureMonkey.giveMeBuilder(TeamTemplate.class)
                .set("id", 1L)
                .set("templateType", templateType)
                .set("question", question)
                .set("checkBoxTemplates", optionNames.stream()
                        .map(option -> new TeamTemplate.CheckboxTemplate(
                                1L,
                                option
                        ))
                        .toList())
                .sample();
    }

}
