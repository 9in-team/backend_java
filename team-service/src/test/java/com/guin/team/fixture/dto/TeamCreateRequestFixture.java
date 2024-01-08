package com.guin.team.fixture.dto;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.domain.constant.SubjectType;
import com.guin.team.domain.constant.TemplateType;
import com.guin.team.fixture.Fixture;

import java.util.Collections;
import java.util.List;

public class TeamCreateRequestFixture extends Fixture {

    private static final List<TeamCreateRequest.TeamCreateTemplateRequest> DEFAULT_TEAM_TEMPLATE =
            Collections.singletonList(new TeamCreateRequest.TeamCreateTemplateRequest(TemplateType.CHECKBOX, "열심히 하실 건가요?", "네,아니요"));
    private static final List<String> DEFAULT_HASHTAG = Collections.singletonList("스프링");
    private static final List<TeamCreateRequest.TeamCreateRoleRequest> DEFAULT_TEAM_ROLE =
            Collections.singletonList(new TeamCreateRequest.TeamCreateRoleRequest("백엔드", 2));

    public static TeamCreateRequest create(final String subject,
                                           final List<TeamCreateRequest.TeamCreateTemplateRequest> teamCreateTemplateRequests,
                                           final List<String> hashTags,
                                           final List<TeamCreateRequest.TeamCreateRoleRequest> teamCreateRoleRequests) {
        return new TeamCreateRequest(
                subject,
                "content",
                "https://kakaotalk.com/gieofgqnflq24",
                SubjectType.PROJECT,
                teamCreateTemplateRequests,
                hashTags,
                teamCreateRoleRequests
        );
    }

    public static TeamCreateRequest create(final String subject) {
        return create(subject,
                DEFAULT_TEAM_TEMPLATE,
                DEFAULT_HASHTAG,
                DEFAULT_TEAM_ROLE);
    }

    public static TeamCreateRequest create(final String subject, final List<String> hashTags) {
        return create(subject,
                DEFAULT_TEAM_TEMPLATE,
                hashTags,
                DEFAULT_TEAM_ROLE);
    }

    public static TeamCreateRequest create(final String subject, final TeamCreateRequest.TeamCreateTemplateRequest teamCreateTemplateRequests) {
        return create(subject,
                Collections.singletonList(teamCreateTemplateRequests),
                DEFAULT_HASHTAG,
                DEFAULT_TEAM_ROLE);
    }

    public static TeamCreateRequest create(final String subject, final TeamCreateRequest.TeamCreateRoleRequest teamCreateTemplateRequests) {
        return create(subject,
                DEFAULT_TEAM_TEMPLATE,
                DEFAULT_HASHTAG,
                Collections.singletonList(teamCreateTemplateRequests));
    }

}
