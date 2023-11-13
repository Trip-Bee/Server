package com.ssafy.trip.domain.post.controller;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.domain.post.service.PostService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notice/posts")
@RequiredArgsConstructor
public class NoticeController {

    private final PostService postService;
    private final String CATEGORY = "NOTICE";

    @PostMapping
    @PreAuthorize("(hasAuthority('ROLE_ADMIN')) and (#writeRequestDto.writerId == principal.id)")
    public ResponseEntity writeNotice(@RequestBody WritePostRequestDto writePostRequestDto) throws Exception {
        postService.writePost(CATEGORY, writePostRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @PatchMapping("/{postId}")
    @PreAuthorize("(hasAuthority('ROLE_ADMIN')) and (#modifyRequestDto.writerId == principal.id)")
    public ResponseEntity modifyNotice(@RequestBody ModifyPostRequestDto modifyPostRequestDto) throws Exception {
        postService.modifyPost(CATEGORY, modifyPostRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteNotice(@PathVariable Long postId) throws Exception {
        postService.deletePost(postId);
        return ResponseEntity.ok(Response.success());
    }


}
