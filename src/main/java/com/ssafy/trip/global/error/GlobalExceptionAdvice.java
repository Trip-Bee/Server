package com.ssafy.trip.global.error;

import com.ssafy.trip.global.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.ssafy.trip.global.error.exception.ExceptionType.Authentication_EXCEPTION;
import static com.ssafy.trip.global.error.exception.ExceptionType.FORBIDDEN_EXCEPTION;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedExceptionHandler(AccessDeniedException ex) {
        log.warn("======= 인가에러 =======");
        log.warn("ex : {}", ex.getStackTrace());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Response.fail(HttpStatus.FORBIDDEN.name(), FORBIDDEN_EXCEPTION.getErrorMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationExceptionHandler(AuthenticationException ex) {
        log.warn("======= 인증에러 =======");
        log.warn("ex : {}", ex.getStackTrace());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Response.fail(HttpStatus.UNAUTHORIZED.name(), Authentication_EXCEPTION.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity methodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        log.warn("ex : {}", ex.getStackTrace());
        return ResponseEntity.badRequest()
                .body(Response.fail(HttpStatus.BAD_REQUEST.name(), "잘못된 요청입니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleDefaultExcpeiton(Exception ex) {

//        log.warn("ex : {}", ex.getStackTrace());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.fail(HttpStatus.BAD_REQUEST.name(), "기본 에러"));
    }
}
