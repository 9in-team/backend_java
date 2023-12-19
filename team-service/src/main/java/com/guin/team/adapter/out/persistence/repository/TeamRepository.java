package com.guin.team.adapter.out.persistence.repository;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    TeamEntity save(final TeamEntity teamEntity);

    Optional<TeamEntity> findById(final Long id);

    List<TeamEntity> findAll();

}
