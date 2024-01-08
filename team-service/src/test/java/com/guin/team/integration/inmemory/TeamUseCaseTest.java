package com.guin.team.integration.inmemory;

import com.guin.team.adapter.out.persistence.entity.HashTagEntity;
import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.port.in.TeamUseCase;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.application.service.TeamService;
import com.guin.team.domain.vo.Team;
import com.guin.team.fixture.command.TeamCommandFixture;
import com.guin.team.integration.IntegrationTest;
import com.guin.team.fixture.mapper.TeamTestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TeamUseCaseTest extends IntegrationTest {

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
        final TeamCommand command = TeamCommandFixture.create("제목");

        Team team = teamUseCase.save(command);

        TeamEntity teamEntity = teamRepository.findById(team.id())
                .orElseThrow(RuntimeException::new);

        assertThat(team).isEqualTo(new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl(),
                team.hashTag(),
                team.teamRoles(),
                team.teamTemplates()
        ));
    }

    @DisplayName("제목이 null이면 에러를 반환한다.")
    @Test
    void nullSubjectInvalidException() {
        final TeamCommand command = TeamCommandFixture.create(null);

        assertThatThrownBy(() -> {
            teamUseCase.save(command);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @DisplayName("해시태그를 저장한다.")
    @Test
    void saveHashTag() {
        final TeamCommand command = TeamCommandFixture.create("제목", List.of("스프링"));

        Team team = teamUseCase.save(command);

        TeamEntity teamEntity = teamRepository.findById(team.id())
                .orElseThrow(RuntimeException::new);

        assertThat(team).isEqualTo(new Team(
                teamEntity.getId(),
                teamEntity.getLeaderId(),
                teamEntity.getSubject(),
                teamEntity.getContent(),
                teamEntity.getSubjectType(),
                teamEntity.getOpenChatUrl(),
                toHashTagList(teamEntity.getHashTags()),
                TeamTestMapper.toTeamRoles(teamEntity.getTeamRoles()),
                TeamTestMapper.toTeamTemplate(teamEntity.getTeamTemplates())
        ));
    }

    private List<String> toHashTagList(List<HashTagEntity> hashTagEntities) {
        return hashTagEntities.stream()
                .map(HashTagEntity::getHashTag)
                .collect(Collectors.toList());
    }

    @DisplayName("해시태그 글자가 11개 이상이면 에러가 발생한다.")
    @Test
    void isMaxLengthHashTag() {
        final TeamCommand command = TeamCommandFixture.create("제목", List.of("Spring framework"));
        assertThatThrownBy(() -> {
            teamUseCase.save(command);
        });
    }

}
