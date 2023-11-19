package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.LoginRequestDto;
import com.ssafy.trip.domain.user.dto.ReissueDto;
import com.ssafy.trip.domain.user.dto.SignupRequestDto;
import com.ssafy.trip.domain.user.service.AuthService;
import com.ssafy.trip.global.dto.Response;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto) {

        log.debug("nickname {}", signupRequestDto.getNickname());
        log.debug("email {}", signupRequestDto.getEmail());
        log.debug("password {}", signupRequestDto.getPassword());
        log.debug("profileImage {}", signupRequestDto.getProfileImage());

        authService.signup(signupRequestDto.toEntity());
        return ResponseEntity.ok(Response.success());
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequestDto request) {
//        UserInfoDto userInfoDto = authService.login(request.getEmail(), request.getPassword());
//        TokenDto tokenDto = jwtService.issueToken(userInfoDto);
//
//        log.debug("accessToken: {}", tokenDto.getAccessToken());
//        log.debug("refreshToken: {}", tokenDto.getRefreshToken());
//
//        return ResponseEntity.ok(Response.success(tokenDto));
//
////        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
////                .userInfo(userInfoDto)
////                .token(tokenDto)
////                .build();
////        return ResponseEntity.ok(Response.success(loginResponseDto));
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        log.debug("============= email {}", loginRequestDto.getEmail());
        log.debug("============= password {}", loginRequestDto.getPassword());

        UserInfoDto userInfoDto = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        TokenDto tokenDto = jwtService.issueToken(userInfoDto);

        log.debug("accessToken: {}", tokenDto.getAccessToken());
        log.debug("refreshToken: {}", tokenDto.getRefreshToken());
        log.debug("access expired {}", tokenDto.getAccessTokenExpired());
        log.debug("refresh expired {}", tokenDto.getRefreshTokenExpired());

        Cookie accessTokenCookie = new Cookie("accessToken", tokenDto.getAccessToken());
        accessTokenCookie.setMaxAge((int)tokenDto.getAccessTokenExpired());
        accessTokenCookie.setPath("/");
        Cookie refreshTokenCookie = new Cookie("refreshToken", tokenDto.getRefreshToken());
        refreshTokenCookie.setMaxAge((int)tokenDto.getRefreshTokenExpired());
        refreshTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(Response.success());
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity logout(@RequestHeader("Authorization") String accessToken,
                                 @CookieValue("accessToken") Cookie access,
                                 @CookieValue("refreshToken") Cookie refresh, HttpServletResponse response) {
        jwtService.addBlackList(accessToken);

        // TODO 쿠키 setMax(0);
        access.setMaxAge(0);
        refresh.setMaxAge(0);
        response.addCookie(access);
        response.addCookie(refresh);

        return ResponseEntity.ok(Response.success());
    }

//    @PostMapping("/reissue")
//    public ResponseEntity reissue(@RequestBody ReissueDto reissueDto) {
//        TokenDto reissuedToken = authService.reissue(reissueDto.getRefreshToken());
//
//        return ResponseEntity.ok(Response.success(reissuedToken));
//    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody ReissueDto reissueDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.reissue(reissueDto.getRefreshToken());

        log.debug("access expired {}", tokenDto.getAccessTokenExpired());
        log.debug("refresh expired {}", tokenDto.getRefreshTokenExpired());

        Cookie accessTokenCookie = new Cookie("accessToken", tokenDto.getAccessToken());
        accessTokenCookie.setMaxAge((int)tokenDto.getAccessTokenExpired());
        accessTokenCookie.setPath("/");
        Cookie refreshTokenCookie = new Cookie("refreshToken", tokenDto.getRefreshToken());
        refreshTokenCookie.setMaxAge((int)tokenDto.getRefreshTokenExpired());
        refreshTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(Response.success());
    }
}
