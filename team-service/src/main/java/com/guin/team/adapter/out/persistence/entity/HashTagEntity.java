package com.guin.team.adapter.out.persistence.entity;

import com.guin.team.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "hashTag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    private Long id;

    @Comment("해시태그")
    @Column(name = "hash_tag", nullable = false, length = 10)
    private String hashTag;

    public HashTagEntity(final String hashTag) {
        this.hashTag = hashTag;
    }
}
