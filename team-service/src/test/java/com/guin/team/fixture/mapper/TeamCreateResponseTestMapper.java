package com.guin.team.fixture.mapper;

import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.entity.TeamRoleEntity;
import com.guin.team.adapter.out.persistence.entity.TeamTemplateEntity;
import com.guin.team.domain.constant.SubjectType;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.stream.Collectors;

public class TeamCreateResponseTestMapper {

    public static TeamCreateResponse create(TeamEntity teamEntity) {
        return new TeamCreateResponse(
                teamEntity.getId(),
                teamEntity.getOpenChatUrl(),
                teamEntity.getContent(),
                teamEntity.getSubject(),
                teamEntity.getSubjectType(),
                toTemplateDetail(teamEntity.getTeamTemplates()),
                toHashTag(teamEntity.getHashTags()),
                toRoleDetail(teamEntity.getTeamRoles())
        );
    }

    private static List<TeamCreateResponse.TeamRoleDetail> toRoleDetail(List<TeamRoleEntity> teamRoleEntities) {
        return teamRoleEntities.stream()
                .map(teamRoleEntity -> new TeamCreateResponse.TeamRoleDetail(
                        teamRoleEntity.getName(),
                        teamRoleEntity.getRequiredCount(),
                        teamRoleEntity.getHiredCount()
                ))
                .toList();
    }

    private static List<String> toHashTag(List<HashTagEntity> hashTagEntities) {
        return hashTagEntities.stream()
                .map(HashTagEntity::getHashTag)
                .toList();
    }

    private static List<TeamCreateResponse.TeamTemplateDetail> toTemplateDetail(List<TeamTemplateEntity> teamTemplateEntities) {
        return teamTemplateEntities.stream()
                .map(teamTemplateEntity ->  new TeamCreateResponse.TeamTemplateDetail(
                        teamTemplateEntity.getTemplateType(),
                        teamTemplateEntity.getQuestion(),
                        teamTemplateEntity.getCheckBoxTemplates().getCheckBoxTemplates().stream()
                                .map(checkBoxTemplateEntity -> checkBoxTemplateEntity.getOptionName())
                                .collect(Collectors.joining(","))
                ))
                .toList();
    }

    public static TeamCreateResponse create(JsonPath response) {
        return new TeamCreateResponse(
                response.getLong("teamId"),
                response.getString("openChatUrl"),
                response.getString("content"),
                response.getString("subject"),
                SubjectType.from(response.getString("subjectType")),
                response.getList("teamTemplates", TeamCreateResponse.TeamTemplateDetail.class),
                response.getList("hashTags", String.class),
                response.getList("teamRoleDetail", TeamCreateResponse.TeamRoleDetail.class)
        );
    }


}
