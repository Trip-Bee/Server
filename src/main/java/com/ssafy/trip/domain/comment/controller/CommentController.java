package com.ssafy.trip.domain.comment.controller;

import com.ssafy.trip.domain.comment.dto.CommentResponseDto;
import com.ssafy.trip.domain.comment.dto.ModifyCommentRequestDto;
import com.ssafy.trip.domain.comment.dto.WriteCommentRequestDto;
import com.ssafy.trip.domain.comment.service.CommentService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{category}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping
    @PreAuthorize("((#category == 'qna' and hasAuthority('ROLE_ADMIN')) " +
            "or (#category != 'qna' and (hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))))")
    public ResponseEntity writeComment(@PathVariable String category, @RequestBody WriteCommentRequestDto writeCommentRequestDto) throws Exception {
        commentService.writeComment(writeCommentRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @GetMapping
    public ResponseEntity getComments(@RequestParam Long postId) throws Exception {
        List<CommentResponseDto> list = commentService.getComments(postId);
        return ResponseEntity.ok(Response.success(list));
    }

    @PatchMapping("/{commentId}")
    @PreAuthorize("((#category == 'qna' and hasAuthority('ROLE_ADMIN')) " +
            "or (#category != 'qna' and (hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))))")
    public ResponseEntity modifyComment(@PathVariable String category, @RequestBody ModifyCommentRequestDto modifyCommentRequestDto) throws Exception {
        commentService.modifyComment(modifyCommentRequestDto);
        return ResponseEntity.ok(Response.success());
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("((#category == 'qna' and hasAuthority('ROLE_ADMIN')) " +
            "or (#category != 'qna' and (hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN'))))")
    public ResponseEntity deleteComment(@PathVariable String category, @PathVariable Long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(Response.success());
    }

}
