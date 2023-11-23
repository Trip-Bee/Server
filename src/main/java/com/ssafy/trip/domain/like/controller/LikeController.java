package com.ssafy.trip.domain.like.controller;

import com.ssafy.trip.domain.like.dto.LikeDto;
import com.ssafy.trip.domain.like.service.LikeService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#likeDto.userId == authentication.principal.id)")
    public ResponseEntity like(@RequestBody LikeDto likeDto) throws Exception {
        likeService.addOrDeleteLike(likeDto.getUserId(), likeDto.getSpotId());
        return ResponseEntity.ok(Response.success());
    }

    @GetMapping
    public ResponseEntity count(@RequestParam int spotId) throws Exception {
        int cnt = likeService.count(spotId);
        return ResponseEntity.ok(Response.success(cnt));
    }


}
