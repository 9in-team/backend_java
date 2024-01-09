package com.guin.team.application.port.in.mapper;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TeamCommandMapper {

    public List<TeamCreateResponse.TeamTemplateDetail> toTeamTemplateDetail(List<TeamTemplate> teamTemplates) {
        return teamTemplates.stream()
                .map(teamTemplate -> new TeamCreateResponse.TeamTemplateDetail(
                        teamTemplate.templateType(),
                        teamTemplate.question(),
                        teamTemplate.checkBoxTemplates().stream()
                                .map(TeamTemplate.CheckboxTemplate::optionName)
                                .collect(Collectors.joining(","))
                )).toList();
    }

    public List<TeamCreateResponse.TeamRoleDetail> toTeamRoleDetail(List<TeamRole> teamRoles) {
        return teamRoles.stream()
                .map(teamRole -> new TeamCreateResponse.TeamRoleDetail(
                        teamRole.name(),
                        teamRole.requiredCount(),
                        teamRole.hiredCount()
                ))
                .toList();
    }

    public TeamCommand toCommand(final TeamCreateRequest request) {
        final List<TeamCommand.TeamTemplate> teamTemplates = toTeamTemplate(request);
        final List<TeamCommand.TeamRole> teamRoles = toTeamRole(request);

        return new TeamCommand(
                request.subject(),
                request.content(),
                request.subjectType(),
                request.openChatUrl(),
                request.hashTags(),
                teamTemplates,
                teamRoles
        );
    }

    public List<TeamCommand.TeamTemplate> toTeamTemplate(final TeamCreateRequest request) {
        return Optional.ofNullable(request.teamTemplates())
                .map(template -> template.stream()
                        .map(teamCreateTemplateRequest -> new TeamCommand.TeamTemplate(
                                teamCreateTemplateRequest.type(),
                                teamCreateTemplateRequest.question(),
                                teamCreateTemplateRequest.option()))
                        .toList()
                )
                .orElse(Collections.emptyList());
    }

    public List<TeamCommand.TeamRole> toTeamRole(final TeamCreateRequest request) {
        return Optional.ofNullable(request.roles())
                .map(roles -> roles.stream()
                        .map(teamCreateRoleRequest -> new TeamCommand.TeamRole(
                                teamCreateRoleRequest.name(),
                                teamCreateRoleRequest.requiredCount()))
                        .toList()
                )
                .orElse(Collections.emptyList());
    }

}
