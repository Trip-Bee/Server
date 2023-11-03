package com.ssafy.trip.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExcpeiton(Exception ex) {
        ex.printStackTrace();
        return null;
    }
}
