package com.guin.exception;

import com.guin.exception.dto.ErrorReportRequest;
import com.guin.exception.dto.ErrorResponse;
import com.guin.exception.error.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.guin.exception.constant.ExceptionCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final String errorMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(INVALID_FIELD_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBody() {
        final ErrorResponse exceptionResponse = ErrorResponse.from(INVALID_REQUEST_BODY);
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleNotSupportedMethod() {
        final ErrorResponse exceptionResponse = ErrorResponse.from(INVALID_HTTP_METHOD_REQUEST);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception e,
                                                                   final HttpServletRequest request) {
        ErrorReportRequest errorReport = new ErrorReportRequest(request, e);
        log.error(errorReport.getLogMessage(), e);

        ErrorResponse exceptionResponse = ErrorResponse.from(INTERNAL_SEVER_ERROR);
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(final AuthException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.from(UNAUTHORIZED_KEY));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException e) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(INTERNAL_SEVER_ERROR.name(), e.getMessage()));
    }

}
