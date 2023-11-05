package com.ssafy.trip.domain.post.controller;

import com.ssafy.trip.domain.post.dto.PostDto;
import com.ssafy.trip.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#postDto.writerId == principal.id)")
    public ResponseEntity<?> registerPost(@RequestBody PostDto postDto) throws Exception {
        postService.registerPost(postDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) throws Exception {
        PostDto postDto = postService.getPost(postId);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam Map<String, String> map) throws Exception {
        List<PostDto> list = postService.getPosts(map);
        return ResponseEntity.ok().body(list);
    }

    @PatchMapping("/{writerId}/{postId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#writerId == principal.id)")
    public ResponseEntity<?> modifyPost(@PathVariable Long writerId, @PathVariable Long postId, @RequestBody PostDto postDto) throws Exception {
        postService.modifyPost(postDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{writerId}/{postId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#writerId == principal.id)")
    public ResponseEntity<?> deletePost(@PathVariable Long writerId, @PathVariable Long postId) throws Exception {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

}
