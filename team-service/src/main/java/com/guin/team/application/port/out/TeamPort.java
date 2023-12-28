package com.guin.team.application.port.out;

import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.Team;

public interface TeamPort {

    Team save(Team team);

}
