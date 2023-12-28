package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.CheckBoxTemplateEntity;
import com.guin.team.adapter.out.persistence.entity.TeamTemplateEntity;
import com.guin.team.domain.vo.TeamTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamTemplateMapper {

    public TeamTemplateEntity toEntity(TeamTemplate teamTemplate) {
        return new TeamTemplateEntity(
                teamTemplate.templateType(),
                teamTemplate.question(),
                toCheckboxTemplateEntity(teamTemplate.checkBoxTemplates())
        );
    }

    private List<CheckBoxTemplateEntity> toCheckboxTemplateEntity(List<TeamTemplate.CheckboxTemplate> checkboxTemplates) {
        return checkboxTemplates.stream()
                .map(checkboxTemplate -> new CheckBoxTemplateEntity(checkboxTemplate.optionName()))
                .toList();
    }

}
