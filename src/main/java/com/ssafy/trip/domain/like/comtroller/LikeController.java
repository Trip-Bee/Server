package com.ssafy.trip.domain.like.comtroller;

import com.ssafy.trip.domain.like.dto.LikeDto;
import com.ssafy.trip.domain.like.service.LikeService;
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
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#likeDto.userId == principal.id)")
    public ResponseEntity<?> addLike(@RequestBody LikeDto likeDto) throws Exception {
        likeService.addLike(likeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{likeId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))")
    public ResponseEntity<?> deleteLike(@PathVariable Long likeId) throws Exception {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> count(@RequestParam Long spotId) throws Exception {
        int cnt = likeService.count(spotId);
        return ResponseEntity.ok().body(cnt);
    }


}