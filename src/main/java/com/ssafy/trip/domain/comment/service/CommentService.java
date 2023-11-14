package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentResponseDto;
import com.ssafy.trip.domain.comment.dto.ModifyCommentRequestDto;
import com.ssafy.trip.domain.comment.dto.WriteCommentRequestDto;

import java.util.List;

public interface CommentService {

    void writeComment(WriteCommentRequestDto writeCommentRequestDto) throws Exception;
    List<CommentResponseDto> getComments(Long postId) throws Exception;
    void modifyComment(ModifyCommentRequestDto modifyCommentRequestDto) throws Exception;
    void deleteComment(Long commentId) throws Exception;

}
