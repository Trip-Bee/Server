package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.domain.user.dto.UpdateDto;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.service.UserService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(Response.success(user));
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
}
