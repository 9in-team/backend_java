package com.guin.team.acceptance;

import com.guin.team.acceptance.step.TeamControllerStep;
import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import com.guin.team.fixture.dto.TeamCreateResponseFixture;
import com.guin.team.fixture.mapper.TeamCreateResponseTestMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        TeamEntity teamEntity = teamRepository.findAll().getFirst();
        TeamCreateResponse actual = TeamCreateResponseTestMapper.create(teamEntity);

        assertThat(actual).isEqualTo(TeamCreateResponseTestMapper.create(response));
    }

    @DisplayName("제목값이 누락될 경우 에러를 반환한다.")
    @Test
    void invalidParameter() {
        TeamCreateRequest errorRequest = TeamCreateRequestFixture.create(null);

        ExtractableResponse<Response> response = TeamControllerStep.create(errorRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("필수값만 요청으로 보내도 모집글이 생성된다.")
    @Test
    void requiredParamCreateProject() {
        Map<String, Object> params = new HashMap<>();
        params.put("subject", "테스트 제목");
        params.put("content", "테스트 내용");
        params.put("openChatUrl", "테스트 오픈 채팅방");
        params.put("subjectType", SubjectType.PROJECT);

        ExtractableResponse<Response> response = TeamControllerStep.create(params);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
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

    @DisplayName("질문 템플릿은 250자를 넘길시 에러를 반환한다.")
    @Test
    void maxLengthQuestionExceptionTest() {
        String maxLengthQuestion = " ".repeat(251);

        TeamCreateRequest request = TeamCreateRequestFixture.create("제목", new TeamCreateRequest.TeamCreateTemplateRequest(TemplateType.CHECKBOX, maxLengthQuestion, "네,아니요"));
        ExtractableResponse<Response> response = TeamControllerStep.create(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    
    @DisplayName("체크박스 템플릿에서 체크박스 옵션이 없으면 에러를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = TemplateType.class)
    void checkboxOptionExceptionTest(TemplateType templateType) {
        TeamCreateRequest request = TeamCreateRequestFixture.create("제목", new TeamCreateRequest.TeamCreateTemplateRequest(templateType, "잘 할것인가요?", null));
        ExtractableResponse<Response> response = TeamControllerStep.create(request);

        assertThat(response.statusCode()).isEqualTo(templateType == TemplateType.CHECKBOX ? HttpStatus.BAD_REQUEST.value() : HttpStatus.CREATED.value());
    }

    @DisplayName("팀 모집군 이름은 30자를 넘을 시 에러를 반환한다.")
    @Test
    void maxLengthTeamRoleException() {
        TeamCreateRequest request = TeamCreateRequestFixture.create("제목", new TeamCreateRequest.TeamCreateRoleRequest(" ".repeat(31), 30));
        ExtractableResponse<Response> response = TeamControllerStep.create(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("해시태그가 6개 이상일때 에러를 반환한다.")
    @Test
    void maxHashTagExceptionTest() {
        TeamCreateRequest maxHashTagRequest = TeamCreateRequestFixture.create("제목", List.of("자바", "스프링", "JPA", "MYSQL", "AWS", "코틀린"));
        ExtractableResponse<Response> response = TeamControllerStep.create(maxHashTagRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("해시태그 글자가 11개 이상이면 에러를 반환한다.")
    @Test
    void maxHashTagLengthExceptionTest() {
        TeamCreateRequest maxLengthHashTagRequest = TeamCreateRequestFixture.create("제목", List.of("Spring Framework"));
        ExtractableResponse<Response> response = TeamControllerStep.create(maxLengthHashTagRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
