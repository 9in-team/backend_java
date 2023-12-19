package com.guin.team.integration.inmemory;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.TeamService;
import com.guin.team.domain.vo.Team;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import com.guin.team.integration.ServiceTest;
import com.guin.team.port.in.TeamUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TeamUseCaseTest extends ServiceTest {

    private final TeamUseCase teamUseCase;
    private final TeamRepository teamRepository;

    public TeamUseCaseTest(final TeamService teamService,
                           final TeamRepository teamRepository) {
        this.teamUseCase = teamService;
        this.teamRepository = teamRepository;
    }

    @BeforeEach
    void setUp() {
        super.setup();
    }

    @DisplayName("모집글을 저장한다.")
    @Test
    void save() {
        TeamCreateRequest request = TeamCreateRequestFixture.create("제목");

        Team team = teamUseCase.save(request);

        TeamEntity teamEntity = teamRepository.findById(team.id())
                .orElseThrow(RuntimeException::new);

        assertThat(team).isEqualTo(new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl()
        ));
    }

    @DisplayName("제목이 null이면 에러를 반환한다.")
    @Test
    void nullSubjectInvalidException() {
        TeamCreateRequest request = TeamCreateRequestFixture.create(null);

        assertThatThrownBy(() -> {
            teamUseCase.save(request);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

}
