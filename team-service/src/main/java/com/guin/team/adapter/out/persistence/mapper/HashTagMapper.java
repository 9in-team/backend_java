package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HashTagMapper {

    public List<HashTagEntity> toEntity(final List<String> hashTags) {
        return Optional.ofNullable(hashTags)
                .map(hashTag -> hashTag.stream()
                        .map(HashTagEntity::new)
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }
}
