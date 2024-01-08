package com.guin.team.fixture.mapper;

import com.guin.team.adapter.out.persistence.entity.CheckBoxTemplateEntity;
import com.guin.team.adapter.out.persistence.entity.TeamRoleEntity;
import com.guin.team.adapter.out.persistence.entity.TeamTemplateEntity;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;

import java.util.List;

public class TeamTestMapper {

    public static List<TeamRole> toTeamRoles(final List<TeamRoleEntity> teamRoleEntities) {
        return teamRoleEntities.stream()
                .map(teamRole -> new TeamRole(
                        teamRole.getId(),
                        teamRole.getName(),
                        teamRole.getRequiredCount(),
                        teamRole.getHiredCount()
                )).toList();
    }

    public static List<TeamTemplate> toTeamTemplate(List<TeamTemplateEntity> teamTemplateEntities) {
        return teamTemplateEntities.stream()
                .map(teamTemplateEntity -> new TeamTemplate(
                        teamTemplateEntity.getId(),
                        teamTemplateEntity.getTemplateType(),
                        teamTemplateEntity.getQuestion(),
                        toCheckBoxTemplate(teamTemplateEntity.getCheckBoxTemplates().getCheckBoxTemplates())
                ))
                .toList();
    }

    private static List<TeamTemplate.CheckboxTemplate> toCheckBoxTemplate(List<CheckBoxTemplateEntity> checkBoxTemplateEntities) {
        return checkBoxTemplateEntities.stream()
                .map(checkBoxTemplateEntity -> new TeamTemplate.CheckboxTemplate(
                        checkBoxTemplateEntity.getId(),
                        checkBoxTemplateEntity.getOptionName()
                ))
                .toList();
    }

}
