package com.guin.team.adapter.out.persistence.repository;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeamRepository extends TeamRepository, JpaRepository<TeamEntity, Long> {
}
