package com.guin.team.application;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.application.dto.TeamCommand;
import com.guin.team.domain.vo.Team;
import com.guin.team.port.in.TeamUseCase;
import com.guin.team.port.out.TeamPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService implements TeamUseCase {

    private final TeamPort teamPort;

    @Override
    @Transactional
    public Team save(final TeamCreateRequest request) {
        return teamPort.save(
                new TeamCommand(
                        request.subject(),
                        request.content(),
                        request.subjectType(),
                        request.openChatUrl()
                )
        );
    }
}
