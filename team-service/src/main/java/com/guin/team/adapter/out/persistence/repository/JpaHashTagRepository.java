package com.guin.team.adapter.out.persistence.repository;

import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHashTagRepository extends HashTagRepository, JpaRepository<HashTagEntity, Long> {
}
