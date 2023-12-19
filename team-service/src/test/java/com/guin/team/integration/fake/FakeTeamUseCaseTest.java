package com.guin.team.integration.fake;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.out.persistence.TeamPersistenceAdapter;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.mapper.TeamMapper;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.TeamService;
import com.guin.team.domain.vo.Team;
import com.guin.team.fixture.dto.TeamCreateRequestFixture;
import com.guin.team.integration.fake.repository.FakeTeamRepository;
import com.guin.team.port.in.TeamUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeTeamUseCaseTest {

    private final TeamUseCase teamUseCase;
    private final TeamRepository teamRepository;

    private TeamCreateRequest request;

    public FakeTeamUseCaseTest() {
        this.teamRepository = new FakeTeamRepository();
        this.teamUseCase = new TeamService(
                new TeamPersistenceAdapter(
                        this.teamRepository,
                        new TeamMapper()
                )
        );

        this.request = TeamCreateRequestFixture.create("제목");
    }

    @DisplayName("모집글을 생성한다.")
    @Test
    void createProject() {
        Team actual = teamUseCase.save(request);
        TeamEntity teamEntity = teamRepository.findAll().get(0);

        assertThat(actual).isEqualTo(new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl()
        ));
    }

}
