package com.ssafy.trip.domain.comment.controller;

import com.ssafy.trip.domain.comment.dto.CommentDto;
import com.ssafy.trip.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#commentDto.writerId == principal.id)")
    public ResponseEntity<?> registerComment(@RequestBody CommentDto commentDto) throws Exception {
        commentService.registerComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getComments(@RequestParam Long postId) throws Exception {
        List<CommentDto> list = commentService.getComments(postId);
        return ResponseEntity.ok().body(list);
    }

    @PatchMapping("/{commentId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')) and (#commentDto.writerId == principal.id)")
    public ResponseEntity<?> modifyComment(@RequestBody CommentDto commentDto) throws Exception {
        commentService.modifyComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("(hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

}
