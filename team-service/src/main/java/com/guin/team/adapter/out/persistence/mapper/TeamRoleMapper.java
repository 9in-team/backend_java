package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.TeamRoleEntity;
import com.guin.team.domain.vo.TeamRole;
import org.springframework.stereotype.Component;

@Component
public class TeamRoleMapper {

    public TeamRoleEntity toEntity(TeamRole teamRole) {
        return new TeamRoleEntity(
                teamRole.name(),
                teamRole.requiredCount(),
                teamRole.hiredCount()
        );
    }

}
