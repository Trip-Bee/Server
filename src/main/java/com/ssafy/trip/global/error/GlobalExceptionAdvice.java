package com.ssafy.trip.global.error;

import com.ssafy.trip.global.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ssafy.trip.global.error.exception.ExceptionType.Authentication_EXCEPTION;
import static com.ssafy.trip.global.error.exception.ExceptionType.FORBIDDEN_EXCEPTION;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedExceptionHandler(AccessDeniedException e) {
        return ResponseEntity.badRequest()
                .body(Response.fail(HttpStatus.FORBIDDEN.name(), FORBIDDEN_EXCEPTION.getErrorMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationExceptionHandler(AuthenticationException e) {
        return ResponseEntity.badRequest()
                .body(Response.fail(HttpStatus.UNAUTHORIZED.name(), Authentication_EXCEPTION.getErrorMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExcpeiton(Exception ex) {
        ex.printStackTrace();
        return null;
    }
}
