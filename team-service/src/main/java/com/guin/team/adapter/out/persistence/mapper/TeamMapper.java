package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.*;
import com.guin.team.domain.vo.Team;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public Team toDomain(final TeamEntity teamEntity) {
        return new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl(),
                toHashTag(teamEntity.getHashTags()),
                toTeamRoleDomain(teamEntity.getTeamRoles()),
                toTeamTemplateDomain(teamEntity.getTeamTemplates())
        );
    }

    private List<String> toHashTag(final List<HashTagEntity> hashTagEntities) {
        return hashTagEntities.stream()
                .map(HashTagEntity::getHashTag)
                .collect(Collectors.toList());
    }

    private List<TeamRole> toTeamRoleDomain(final List<TeamRoleEntity> teamRoleEntities) {
        return teamRoleEntities.stream()
                .map(teamRoleEntity -> new TeamRole(
                        teamRoleEntity.getId(),
                        teamRoleEntity.getName(),
                        teamRoleEntity.getRequiredCount(),
                        teamRoleEntity.getHiredCount()
                )).toList();
    }

    private List<TeamTemplate> toTeamTemplateDomain(final List<TeamTemplateEntity> teamTemplateEntities) {
        return teamTemplateEntities.stream()
                .map(teamTemplateEntity -> new TeamTemplate(
                        teamTemplateEntity.getId(),
                        teamTemplateEntity.getTemplateType(),
                        teamTemplateEntity.getQuestion(),
                        toCheckBoxTemplateDomain(teamTemplateEntity.getCheckBoxTemplates().getCheckBoxTemplates())
                )).toList();
    }

    private List<TeamTemplate.CheckboxTemplate> toCheckBoxTemplateDomain(final List<CheckBoxTemplateEntity> checkBoxTemplateEntities) {
        return Optional.ofNullable(checkBoxTemplateEntities)
                .map(entities -> entities.stream()
                        .map(checkBoxTemplateEntity -> new TeamTemplate.CheckboxTemplate(
                                checkBoxTemplateEntity.getId(),
                                checkBoxTemplateEntity.getOptionName()
                        ))
                        .toList())
                .orElse(Collections.emptyList());
    }

}
