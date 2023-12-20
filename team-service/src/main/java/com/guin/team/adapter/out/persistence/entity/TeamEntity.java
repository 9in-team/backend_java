package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.domain.constant.SubjectType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leader_id", nullable = false)
    private Long leaderId;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "Subject_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @Column(name = "open_chat_url", length = 500)
    private String openChatUrl;

    public TeamEntity(final Long leaderId,
                      final String subject,
                      final String content,
                      final SubjectType subjectType,
                      final String openChatUrl) {
        this.leaderId = leaderId;
        this.subject = subject;
        this.content = content;
        this.subjectType = subjectType;
        this.openChatUrl = openChatUrl;
    }
}
