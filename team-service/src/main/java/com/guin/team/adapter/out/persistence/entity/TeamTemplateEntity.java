package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.adapter.out.persistence.entity.common.BaseTimeEntity;
import com.guin.team.domain.constant.TemplateType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Table(name = "team_template")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamTemplateEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @Comment("템플렛 타입")
    @Enumerated(EnumType.STRING)
    @Column(name = "template_type", nullable = false, length = 30)
    private TemplateType templateType;

    @Comment("질문")
    @Column(name = "question", nullable = false, length = 250)
    private String question;

    @Comment("체크박스 답변 선택지")
    @Embedded
    private CheckBoxTemplates checkBoxTemplates;

    public TeamTemplateEntity(final TemplateType templateType,
                              final String question,
                              final List<CheckBoxTemplateEntity> checkBoxTemplates) {
        this.templateType = templateType;
        this.question = question;
        this.checkBoxTemplates = CheckBoxTemplates.of(checkBoxTemplates, templateType);
    }
}
