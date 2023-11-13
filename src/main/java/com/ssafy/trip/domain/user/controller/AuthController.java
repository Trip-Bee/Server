package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.LoginRequestDto;
import com.ssafy.trip.domain.user.dto.LoginResponseDto;
import com.ssafy.trip.domain.user.dto.SignupRequestDto;
import com.ssafy.trip.domain.user.service.AuthService;
import com.ssafy.trip.global.dto.Response;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto) {
        authService.signup(signupRequestDto.toEntity());
        return ResponseEntity.ok(Response.success());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto request) {
        UserInfoDto userInfoDto = authService.login(request.getEmail(), request.getPassword());
        TokenDto tokenDto = jwtService.issueToken(userInfoDto);
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .userInfo(userInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok(Response.success(loginResponseDto));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity logout(@RequestHeader("Authorization") String accessToken) {
        jwtService.addBlackList(accessToken);

        return ResponseEntity.ok(Response.success());
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestHeader("Refresh") String refreshToken) {
        TokenDto tokenDto = authService.reissue(refreshToken);

        return ResponseEntity.ok(Response.success(tokenDto));
    }
}
