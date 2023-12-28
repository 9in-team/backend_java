package com.guin.team.domain.vo;

public record TeamRole(
        Long id,
        String name,
        int requiredCount,
        int hiredCount
) {
}
