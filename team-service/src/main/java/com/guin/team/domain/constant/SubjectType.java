package com.guin.team.domain.constant;

import java.util.Arrays;

public enum SubjectType {
    PROJECT,
    STUDY;

    public static SubjectType from(final String type) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
