package com.guin.exception.constant;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INVALID_FIELD_REQUEST("잘못된 요청값으로 요청하였습니다."),
    INVALID_REQUEST_BODY("잘못된 형식의 Request Body 입니다."),
    INVALID_HTTP_METHOD_REQUEST("지원하지 않는 HTTP 메소드 요청입니다."),

    UNAUTHORIZED_KEY("인증되지 않은 액세스 키 혹은 시크릿 키입니다."),

    INTERNAL_SEVER_ERROR("예상하지 못한 서버 에러가 발생했습니다.");

    private final String message;

    ExceptionCode(final String message) {
        this.message = message;
    }
}
