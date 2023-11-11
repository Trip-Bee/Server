package com.ssafy.trip.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "사용할 수 없는 토큰 입니다."),

    CERTIFICATION_EXCEPTION(HttpStatus.UNAUTHORIZED, "자격 증명이 되어 있지 않습니다."),

    FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    Authentication_EXCEPTION(HttpStatus.UNAUTHORIZED, "인증되지 않은 요청입니다."),
    MAIL_SEND_FAILED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_TO_SEND_MAIL");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
