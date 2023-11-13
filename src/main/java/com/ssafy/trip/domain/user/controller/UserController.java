package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.domain.user.dto.PasswordRequestDto;
import com.ssafy.trip.domain.user.dto.UpdateDto;
import com.ssafy.trip.domain.user.dto.UserDetailResponseDto;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.service.UserService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getUserInfo(@PathVariable Long userId) {
        User user = userService.getUserInfo(userId);
        return ResponseEntity.ok(Response.success(UserDetailResponseDto.from(user)));
    }

    @PatchMapping("/{userId}")
//    권한체크, 본인확인 >> 토큰에 저장된 id값과 경로에 포함된 id값이 동일한 사용자만 허용
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))")
    public ResponseEntity updateUser(@PathVariable Long userId, @RequestBody UpdateDto updateDto) {
        userService.updateUser(updateDto.toEntity());
        return ResponseEntity.ok(Response.success());
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#userId == principal.id)")
    public ResponseEntity withdraw(@PathVariable Long userId) {
        userService.updateStatus(userId);
        return ResponseEntity.ok(Response.success());
    }

//    TODO pw변경
//    Mail에 pw변경화면이 있는 프론트 url전송
//    프론트 url?token=accessToken 형태로 전달됨
//    프론트의 pw변경화면 부분에서 token획득
//    token이 없다면 login페이지든 다른 페이지로 이동
//    token이 있다면 해당 토큰을 이용하여 사용자가 입력한 pw를 서버로 전달할때 헤더에 넣어서 전달해줌
    @PatchMapping("/password")
    @PreAuthorize("(hasAuthority('ROLE_USER'))")
    public ResponseEntity updatePassword(@AuthenticationPrincipal LoginUserDto loginUserDto,
                                         @RequestBody PasswordRequestDto passwordRequestDto) {
        userService.updatePassword(loginUserDto.getId(), passwordRequestDto.getPassword());

        return ResponseEntity.ok(Response.success());
    }
}
