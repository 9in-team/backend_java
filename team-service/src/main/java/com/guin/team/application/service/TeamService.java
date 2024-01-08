package com.guin.team.application.service;

import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.application.port.out.TeamPort;
import com.guin.team.domain.vo.Team;
import com.guin.team.domain.vo.TeamRole;
import com.guin.team.domain.vo.TeamTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService implements TeamUseCase {

    private static final Long DEFAULT_ID = 1L;
    private static final String DELIMITER = ",";

    private final TeamPort teamPort;

    @Override
    public Team save(final TeamCommand command) {
        final List<TeamTemplate> teamTemplates = command.teamTemplates().stream()
                .map(teamTemplate -> {
                    List<TeamTemplate.CheckboxTemplate> checkboxTemplates = Optional.ofNullable(teamTemplate.option())
                            .map(options -> Arrays.stream(options.split(DELIMITER))
                                    .map(option -> new TeamTemplate.CheckboxTemplate(DEFAULT_ID, option))
                                    .toList())
                            .orElse(Collections.emptyList());

                    return new TeamTemplate(DEFAULT_ID, teamTemplate.type(), teamTemplate.question(), checkboxTemplates);
                })
                .toList();

        final List<TeamRole> teamRoles = command.teamRoles().stream()
                .map(role -> new TeamRole(DEFAULT_ID, role.name(), role.requiredCount(), 0))
                .toList();

        final Team team = new Team(
                DEFAULT_ID,
                DEFAULT_ID,
                command.subject(),
                command.content(),
                command.subjectType(),
                command.openChatUrl(),
                command.hashTags(),
                teamRoles,
                teamTemplates);

        return teamPort.save(team);
    }
}
