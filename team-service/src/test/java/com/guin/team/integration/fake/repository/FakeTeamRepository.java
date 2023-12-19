package com.guin.team.integration.fake.repository;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;

import java.util.*;

public class FakeTeamRepository implements TeamRepository {

    private Map<UUID, TeamEntity> teams = new HashMap<>();

    @Override
    public TeamEntity save(final TeamEntity teamEntity) {
        teams.put(UUID.randomUUID(), teamEntity);
        return teamEntity;
    }

    @Override
    public Optional<TeamEntity> findById(Long id) {
        return Optional.ofNullable(teams.get(id));
    }

    @Override
    public List<TeamEntity> findAll() {
        return new ArrayList<>(teams.values());
    }
}
