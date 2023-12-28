package com.guin.team.adapter.out.persistence;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.entity.TeamRoleEntity;
import com.guin.team.adapter.out.persistence.entity.TeamTemplateEntity;
import com.guin.team.adapter.out.persistence.mapper.HashTagMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamRoleMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamTemplateMapper;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.port.out.TeamPort;
import com.guin.team.domain.vo.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamPersistenceAdapter implements TeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final HashTagMapper hashTagMapper;
    private final TeamRoleMapper teamRoleMapper;
    private final TeamTemplateMapper teamTemplateMapper;

    @Override
    public Team save(final Team team) {
        final List<TeamRoleEntity> teamRoles = team.teamRoles().stream()
                .map(teamRoleMapper::toEntity)
                .toList();
        final List<TeamTemplateEntity> teamTemplateEntities = team.teamTemplates().stream()
                .map(teamTemplateMapper::toEntity)
                .toList();
        final TeamEntity teamEntity = new TeamEntity(
                team.leaderId(),
                team.subject(),
                team.content(),
                team.subjectType(),
                team.openChatUrl(),
                hashTagMapper.toEntity(team.hashTag()),
                teamRoles,
                teamTemplateEntities);

        final TeamEntity newTeamEntity = teamRepository.save(teamEntity);

        return teamMapper.toDomain(newTeamEntity);
    }
}
