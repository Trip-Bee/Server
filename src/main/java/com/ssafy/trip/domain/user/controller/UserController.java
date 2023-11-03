package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.LoginRequest;
import com.ssafy.trip.domain.user.dto.SignupRequest;
import com.ssafy.trip.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        userService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest.getEmail(), signupRequest.getPassword());
        return ResponseEntity.ok().build();
    }


}
