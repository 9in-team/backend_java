package com.guin.account.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountCreateRequest(
    @NotBlank(message = "아이디는 빈 값을 넣을 수 없습니다.")
    String username,
    @NotBlank(message = "성(First Name)은 빈 값을 넣을 수 없습니다.")
    String firstName,
    @NotBlank(message = "이름(Last Name)은 빈 값을 넣을 수 없습니다.")
    String lastName,
    @NotBlank(message = "이메일은 빈 값을 넣을 수 없습니다.")
    String email
) {

}
