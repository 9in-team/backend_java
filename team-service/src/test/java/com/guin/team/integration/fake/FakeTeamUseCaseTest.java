package com.guin.team.integration.fake;

import com.guin.team.adapter.out.persistence.TeamPersistenceAdapter;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.mapper.HashTagMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamRoleMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamTemplateMapper;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.application.service.TeamService;
import com.guin.team.domain.vo.Team;
import com.guin.team.fixture.command.TeamCommandFixture;
import com.guin.team.integration.fake.repository.FakeTeamRepository;
import com.guin.team.fixture.mapper.TeamTestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeTeamUseCaseTest {

    private final TeamUseCase teamUseCase;
    private final TeamRepository teamRepository;
    private TeamCommand command;

    public FakeTeamUseCaseTest() {
        this.teamRepository = new FakeTeamRepository();
        this.teamUseCase = new TeamService(
                new TeamPersistenceAdapter(
                        this.teamRepository,
                        new TeamMapper(),
                        new HashTagMapper(),
                        new TeamRoleMapper(),
                        new TeamTemplateMapper()
                )
        );

        this.command = TeamCommandFixture.create("제목");
    }

    @DisplayName("모집글을 생성한다.")
    @Test
    void createProject() {
        Team actual = teamUseCase.save(command);
        TeamEntity teamEntity = teamRepository.findAll().get(0);

        assertThat(actual).isEqualTo(new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl(),
                actual.hashTag(),
                TeamTestMapper.toTeamRoles(teamEntity.getTeamRoles()),
                TeamTestMapper.toTeamTemplate(teamEntity.getTeamTemplates())
        ));
    }

}
