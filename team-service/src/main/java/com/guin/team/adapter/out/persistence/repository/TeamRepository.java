package com.guin.team.adapter.out.persistence.repository;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
