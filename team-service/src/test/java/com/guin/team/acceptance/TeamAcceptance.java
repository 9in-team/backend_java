package com.guin.team.acceptance;

import com.guin.team.acceptance.step.TeamControllerStep;
import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import com.guin.team.fixture.dto.TeamCreateResponseFixture;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamAcceptance extends AcceptanceTest {

    private final TeamRepository teamRepository;

    private TeamCreateRequest request;
    private TeamCreateRequest existHashTagRequest;

    public TeamAcceptance(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @BeforeEach
    void setup() {
        super.setUp();
        this.request = TeamCreateRequestFixture.create("제목");
        this.existHashTagRequest = TeamCreateRequestFixture.create("제목", List.of("자바", "스프링", "JPA", "MYSQL", "AWS"));
    }

    @DisplayName("모집글을 생성한다.")
    @Test
    void createProject() {
        JsonPath response = TeamControllerStep.create(request).jsonPath();

        TeamEntity teamEntity = teamRepository.findAll().get(0);

        TeamCreateResponse actual = TeamCreateResponseFixture.create(
                teamEntity.getId(),
                teamEntity.getOpenChatUrl(),
                teamEntity.getContent(),
                teamEntity.getSubject(),
                teamEntity.getSubjectType()
        );

        assertThat(actual).isEqualTo(TeamCreateResponseFixture.create(
                response.getLong("teamId"),
                response.getString("openChatUrl"),
                response.getString("content"),
                response.getString("subject"),
                SubjectType.from(response.getString("subjectType"))
        ));
    }

    @DisplayName("파라미터에 필수값이 누락될 경우 에러를 반환한다.")
    @Test
    void invalidParameter() {
        TeamCreateRequest errorRequest = new TeamCreateRequest(null, null, null, null, null, null, null);

        ExtractableResponse<Response> response = TeamControllerStep.create(errorRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("해시태그가 있는 모집글을 생성한다.")
    @Test
    void existHashTagProjectTeam() {
        JsonPath response = TeamControllerStep.create(existHashTagRequest).jsonPath();

        TeamEntity teamEntity = teamRepository.findAll().get(0);

        TeamCreateResponse actual = TeamCreateResponseFixture.create(
                teamEntity.getId(),
                teamEntity.getOpenChatUrl(),
                teamEntity.getContent(),
                teamEntity.getSubject(),
                teamEntity.getSubjectType(),
                teamEntity.getHashTags().stream().map(HashTagEntity::getHashTag).collect(Collectors.toList())
        );

        assertThat(actual).isEqualTo(TeamCreateResponseFixture.create(
                response.getLong("teamId"),
                response.getString("openChatUrl"),
                response.getString("content"),
                response.getString("subject"),
                SubjectType.from(response.getString("subjectType")),
                existHashTagRequest.hashTags()
        ));
    }

}
