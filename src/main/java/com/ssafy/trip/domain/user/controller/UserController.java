package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.LoginRequestDto;
import com.ssafy.trip.domain.user.dto.LoginResponseDto;
import com.ssafy.trip.domain.user.dto.SignupRequestDto;
import com.ssafy.trip.domain.user.dto.UpdateDto;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.service.UserService;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto request) {
        TokenUserInfoDto tokenUserInfoDto = userService.login(request.getEmail(), request.getPassword());
        TokenDto tokenDto = jwtService.issueToken(tokenUserInfoDto);
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .userInfo(tokenUserInfoDto)
                .token(tokenDto)
                .build();
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
//        Long expiration = jwtService.calculateExpiration(token);
//        userService.logout(token, expiration);
        jwtService.addBlackList(token);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
//  TODO 권한체크, 본인확인 >> 토큰에 저장된 id값과 경로에 포함된 id값이 동일한 사용자만 허용
//    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#userId == principal.id)")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getUserInfo(@PathVariable Long userId) {
        User user = userService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#userId == principal.id)")
    public ResponseEntity updateUser(@PathVariable Long userId, @RequestBody UpdateDto updateDto) {
        userService.updateUser(updateDto.toEntity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#userId == principal.id)")
    public ResponseEntity withdraw(@PathVariable Long userId) {
        userService.updateStatus(userId);
        return ResponseEntity.ok().build();
    }
}
