package com.guin.team.application.port.in;

import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.Team;

public interface TeamUseCase {

    Team save(TeamCommand command);

}
