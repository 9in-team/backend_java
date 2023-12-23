package com.guin.exception.dto;

import com.guin.exception.constant.ExceptionCode;

public record ErrorResponse(
        String code,
        String message
) {

    public static ErrorResponse from(final ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.name(), exceptionCode.getMessage());
    }

}
