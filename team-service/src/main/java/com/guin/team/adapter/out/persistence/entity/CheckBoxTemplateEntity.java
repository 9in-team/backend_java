package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "check_box_template")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckBoxTemplateEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_name", length = 100)
    private String optionName;

    public CheckBoxTemplateEntity(final String optionName) {
        this.optionName = optionName;
    }
}
