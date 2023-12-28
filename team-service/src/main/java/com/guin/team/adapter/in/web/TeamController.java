package com.guin.team.adapter.in.web;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.Team;
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
                        Collections.emptyList(),
                        team.hashTag(),
                        Collections.emptyList()
                ));
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
        return request.teamTemplates().stream()
                .map(teamCreateTemplateRequest -> new TeamCommand.TeamTemplate(
                        teamCreateTemplateRequest.type(),
                        teamCreateTemplateRequest.question(),
                        teamCreateTemplateRequest.option()
                )).toList();
    }

    private List<TeamCommand.TeamRole> toTeamRole(final TeamCreateRequest request) {
        return request.roles().stream()
                .map(teamCreateRoleRequest -> new TeamCommand.TeamRole(
                        teamCreateRoleRequest.name(),
                        teamCreateRoleRequest.requiredCount()
                )).toList();
    }

}
