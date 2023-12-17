package com.guin.team.port.out;

import com.guin.team.application.dto.TeamCommand;
import com.guin.team.domain.vo.Team;

public interface TeamPort {

    Team save(TeamCommand teamCommand);

}
