package com.guin.team.port.in;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.domain.vo.Team;

public interface TeamUseCase {

    Team save(TeamCreateRequest request);

}
