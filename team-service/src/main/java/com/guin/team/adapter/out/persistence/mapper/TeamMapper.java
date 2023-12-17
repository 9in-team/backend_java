package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.domain.vo.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team toMapper(final TeamEntity teamEntity) {
        return new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl()
        );
    }

}
