package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void registerComment(CommentDto commentDto) throws Exception;
    List<CommentDto> getComments(Long postId) throws Exception;
    void modifyComment(CommentDto commentDto) throws Exception;
    void deleteComment(Long commentId) throws Exception;

}
