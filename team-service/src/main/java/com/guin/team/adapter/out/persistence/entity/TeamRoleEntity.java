package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Table(name = "team_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRoleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @Comment("모집군 이름")
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Comment("필요한 인원")
    @Column(name = "required_count", nullable = false)
    private int requiredCount;

    @Comment("고용된 팀원 수")
    @Column(name = "hired_count", nullable = false)
    private int hiredCount;

    public TeamRoleEntity(final String name,
                          final int requiredCount,
                          final int hiredCount) {
        this.name = name;
        this.requiredCount = requiredCount;
        this.hiredCount = hiredCount;
    }
}
