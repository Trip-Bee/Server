package com.ssafy.trip.domain.post.controller;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.domain.post.service.PostService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{category}/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#writeRequestDto.writerId == principal.id)")
    public ResponseEntity writePost(@PathVariable String category, @RequestBody WritePostRequestDto writePostRequestDto) throws Exception {
        postService.writePost(category, writePostRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) throws Exception {
        PostResponseDto postResponseDto = postService.getPost(postId);
        postService.updateHit(postId);
        return ResponseEntity.ok(Response.success(postResponseDto));
    }

    @GetMapping
    public ResponseEntity getPosts(@PathVariable String category) throws Exception {
        List<PostResponseDto> list = postService.getPosts(category);
        return ResponseEntity.ok(Response.success(list));
    }

    @PatchMapping("/{postId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#modifyRequestDto.writerId == principal.id)")
    public ResponseEntity modifyPost(@PathVariable String category, @RequestBody ModifyPostRequestDto modifyPostRequestDto) throws Exception {
        postService.modifyPost(category, modifyPostRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))")
    public ResponseEntity deletePost(@PathVariable Long postId) throws Exception {
        postService.deletePost(postId);
        return ResponseEntity.ok(Response.success());
    }

}
