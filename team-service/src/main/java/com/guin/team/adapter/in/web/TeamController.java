package com.guin.team.adapter.in.web;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.application.port.in.mapper.TeamCommandMapper;
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
    private final TeamCommandMapper teamCommandMapper;

    @PostMapping
    public ResponseEntity<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request) {
        final Team team = teamUseCase.save(teamCommandMapper.toCommand(request));

        return ResponseEntity.created(URI.create("/team/%d".formatted(team.id())))
                .body(new TeamCreateResponse(
                        team.id(),
                        team.openChatUrl(),
                        team.content(),
                        team.subject(),
                        team.subjectType(),
                        teamCommandMapper.toTeamTemplateDetail(team.teamTemplates()),
                        team.hashTag(),
                        teamCommandMapper.toTeamRoleDetail(team.teamRoles())
                ));
    }

}
