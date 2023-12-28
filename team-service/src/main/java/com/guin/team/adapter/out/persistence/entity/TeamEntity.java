package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.adapter.out.persistence.entity.common.BaseTimeEntity;
import com.guin.team.domain.constant.SubjectType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @Comment("팀장 아이디")
    @Column(name = "leader_id", nullable = false)
    private Long leaderId;

    @Comment("제목")
    @Column(name = "subject", nullable = false)
    private String subject;

    @Comment("본문")
    @Column(name = "content", nullable = false)
    private String content;

    @Comment("모집글 형태")
    @Column(name = "subject_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @Comment("오픈 채팅방 URL")
    @Column(name = "open_chat_url", length = 500)
    private String openChatUrl;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "hashTag_id")
    private List<HashTagEntity> hashTags;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "team_role_id")
    private List<TeamRoleEntity> teamRoles;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "team_template_id")
    private List<TeamTemplateEntity> teamTemplates;

    public TeamEntity(final Long leaderId,
                      final String subject,
                      final String content,
                      final SubjectType subjectType,
                      final String openChatUrl,
                      final List<HashTagEntity> hashTags,
                      final List<TeamRoleEntity> teamRoles,
                      final List<TeamTemplateEntity> teamTemplates) {
        this.leaderId = leaderId;
        this.subject = subject;
        this.content = content;
        this.subjectType = subjectType;
        this.openChatUrl = openChatUrl;
        this.hashTags = hashTags;
        this.teamRoles = teamRoles;
        this.teamTemplates = teamTemplates;
    }
}
