package com.guin.team.adapter.out.persistence.mapper;

import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.domain.vo.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public Team toMapper(final TeamEntity teamEntity) {
        return new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl(),
                convertHashTag(teamEntity.getHashTags())
        );
    }

    private List<String> convertHashTag(final List<HashTagEntity> hashTagEntities) {
        return hashTagEntities.stream()
                .map(HashTagEntity::getHashTag)
                .collect(Collectors.toList());
    }

}
