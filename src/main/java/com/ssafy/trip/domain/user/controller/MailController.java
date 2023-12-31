package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.EmailRequestDto;
import com.ssafy.trip.domain.user.service.MailService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/password")
    public ResponseEntity sendPasswordChangeLink(@RequestBody EmailRequestDto emailRequestDto) {
        log.debug("================ 입력받은 email {}", emailRequestDto.getEmail());
        mailService.sendPasswordEmail(emailRequestDto.getEmail());

        return ResponseEntity.ok(Response.success());
    }
}
