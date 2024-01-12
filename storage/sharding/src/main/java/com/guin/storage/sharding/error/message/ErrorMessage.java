package com.guin.storage.sharding.error.message;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    INVALID_SHARDING_KEY("유효하지 않은 샤딩 키입니다."),
    NOT_FOUND_SHARDING_KEY("샤딩키를 찾지 못하였습니다."),
    PARSING_FORMAT_SHARDING_KEY("샤딩키를 파싱하면서 에러가 발생하였습니다."),
    NOT_FOUND_SHARDING_STRATEGY("샤딩방식을 찾을 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
