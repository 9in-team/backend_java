package com.guin.team.application.port.out;

import com.guin.team.application.service.dto.TeamCommand;
import com.guin.team.domain.vo.Team;

public interface TeamPort {

    Team save(TeamCommand teamCommand);

}
