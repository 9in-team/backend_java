package com.guin.team.adapter.in.web;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.Team;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamUseCase teamUseCase;

    @PostMapping
    public ResponseEntity<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request) {
        final Team team = teamUseCase.save(toCommand(request));

        return ResponseEntity.created(URI.create("/team/%d".formatted(team.id())))
                .body(new TeamCreateResponse(
                        team.id(),
                        team.openChatUrl(),
                        team.content(),
                        team.subject(),
                        team.subjectType(),
                        toTeamTemplateDetail(team.teamTemplates()),
                        team.hashTag(),
                        toTeamRoleDetail(team.teamRoles())
                ));
    }

    private List<TeamCreateResponse.TeamTemplateDetail> toTeamTemplateDetail(List<TeamTemplate> teamTemplates) {
        return teamTemplates.stream()
                .map(teamTemplate -> new TeamCreateResponse.TeamTemplateDetail(
                        teamTemplate.templateType(),
                        teamTemplate.question(),
                        teamTemplate.checkBoxTemplates().stream()
                                .map(TeamTemplate.CheckboxTemplate::optionName)
                                .collect(Collectors.joining(","))
                )).toList();
    }

    private List<TeamCreateResponse.TeamRoleDetail> toTeamRoleDetail(List<TeamRole> teamRoles) {
        return teamRoles.stream()
                .map(teamRole -> new TeamCreateResponse.TeamRoleDetail(
                        teamRole.name(),
                        teamRole.requiredCount(),
                        teamRole.hiredCount()
                ))
                .toList();
    }

    private TeamCommand toCommand(final TeamCreateRequest request) {
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

    private List<TeamCommand.TeamTemplate> toTeamTemplate(final TeamCreateRequest request) {
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

    private List<TeamCommand.TeamRole> toTeamRole(final TeamCreateRequest request) {
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
