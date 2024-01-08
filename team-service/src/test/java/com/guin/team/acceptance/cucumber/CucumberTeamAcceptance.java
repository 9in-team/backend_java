package com.guin.team.acceptance.cucumber;

import com.guin.team.acceptance.AcceptanceTest;
import com.guin.team.acceptance.step.TeamControllerStep;
import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import com.guin.team.fixture.mapper.TeamCreateResponseTestMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberTeamAcceptance extends AcceptanceTest {

    private final TeamRepository teamRepository;

    private TeamCreateRequest request;
    private ExtractableResponse<Response> response;
    private TeamCreateResponse actual;
    private TeamCreateResponse expected;

    @LocalServerPort
    int port;

    public CucumberTeamAcceptance(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Before
    public void setup() {
        super.setUp();
        RestAssured.port = port;
    }

    @Given("해시태그와 모집인원, 템플릿 모두 포함한 요청정보를 만든다.")
    public void createRequest() {
        this.request = TeamCreateRequestFixture.create("제목");
    }

    @When("해시태그와 모집인원, 템플릿 정보를 모두 넣어 프로젝트 생성 요청을 한다.")
    public void sendProjectApi() {
        this.response = TeamControllerStep.create(request);
    }

    @Then("정상적으로 응답이 왔는지 확인한다.")
    public void resultSendApi() {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        TeamEntity teamEntity = teamRepository.findAll().getFirst();
        this.actual = TeamCreateResponseTestMapper.create(teamEntity);
        this.expected = TeamCreateResponseTestMapper.create(response.jsonPath());
    }

    @And("응답값과 저장된 값이 일치한지 확인한다.")
    public void equalsResponseAndSave() {
        assertThat(actual).isEqualTo(expected);
    }

}
