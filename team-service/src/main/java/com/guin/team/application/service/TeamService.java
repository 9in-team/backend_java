package com.guin.team.application.service;

import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.application.port.out.TeamPort;
import com.guin.team.domain.vo.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService implements TeamUseCase {

    private final TeamPort teamPort;

    @Override
    public Team save(final TeamCommand command) {
        return teamPort.save(command);
    }
}
