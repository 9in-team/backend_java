package com.guin.team.application.service;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.application.service.dto.TeamCommand;
import com.guin.team.domain.vo.Team;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.out.TeamPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService implements TeamUseCase {

    private final TeamPort teamPort;

    @Override
    public Team save(final TeamCreateRequest request) {
        return teamPort.save(
                new TeamCommand(
                        request.subject(),
                        request.content(),
                        request.subjectType(),
                        request.openChatUrl(),
                        request.hashTags()
                )
        );
    }
}
