package com.guin.team.documentation;

import com.guin.team.adapter.in.web.TeamController;
import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.mapper.TeamCommandMapper;
import com.guin.team.application.service.TeamService;
import com.guin.team.fixture.domain.TeamFixture;
import com.guin.team.fixture.domain.TeamRoleFixture;
import com.guin.team.fixture.domain.TeamTemplateFixture;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.guin.team.documentation.step.TeamDocumentStep.resultHandler;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamDocumentationTest extends Documentation {

    private TeamController teamController;
    private TeamUseCase teamUseCase;
    private TeamCommandMapper teamCommandMapper = new TeamCommandMapper();

    @BeforeEach
    void setUp() {
        teamUseCase = mock(TeamService.class);
        teamController = new TeamController(teamUseCase, teamCommandMapper);
        mockMvc = mockController(teamController);
    }

    @DisplayName("모집글 생성하는 restdocs를 생성한다.")
    @Test
    void createProject() {
        final TeamCreateRequest request = TeamCreateRequestFixture.create("테스트 제목");

        when(teamUseCase.save(any())).thenReturn(TeamFixture.create(
                        request.subject(),
                        request.content(),
                        request.subjectType(),
                        request.openChatUrl(),
                        request.hashTags(),
                        request.roles().stream()
                                .map(role -> TeamRoleFixture.create(role.name(), role.requiredCount(), 0))
                                .toList(),
                        request.teamTemplates().stream()
                                .map(teamTemplate -> TeamTemplateFixture.create(teamTemplate.type(),
                                        teamTemplate.question(),
                                        Arrays.stream(teamTemplate.option().split(",")).collect(Collectors.toList())))
                                .toList()
                )
        );

        given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .log().all()
                .post("/team")
                .then()
                .log().all()
                .status(HttpStatus.CREATED)
                .apply(resultHandler());

    }

}
