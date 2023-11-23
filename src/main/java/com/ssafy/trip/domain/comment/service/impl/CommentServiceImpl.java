package com.ssafy.trip.domain.comment.service.impl;

import com.ssafy.trip.domain.comment.dto.CommentResponseDto;
import com.ssafy.trip.domain.comment.dto.ModifyCommentRequestDto;
import com.ssafy.trip.domain.comment.dto.WriteCommentRequestDto;
import com.ssafy.trip.domain.comment.entity.Comment;
import com.ssafy.trip.domain.comment.mapper.CommentMapper;
import com.ssafy.trip.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public void writeComment(WriteCommentRequestDto writeCommentRequestDto) throws Exception {
        Comment comment = Comment.builder()
                .writerId(writeCommentRequestDto.getWriterId())
                .postId(writeCommentRequestDto.getPostId())
                .content(writeCommentRequestDto.getContent())
                .build();
        commentMapper.save(comment);
    }

    @Override
    public List<CommentResponseDto> getComments(Long postId) throws Exception {
        return commentMapper.findAllByPostId(postId)
                .stream().map(Comment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void modifyComment(ModifyCommentRequestDto modifyCommentRequestDto) throws Exception {
        Comment comment = Comment.builder()
                .id(modifyCommentRequestDto.getId())
                .writerId(modifyCommentRequestDto.getWriterId())
                .postId(modifyCommentRequestDto.getPostId())
                .content(modifyCommentRequestDto.getContent())
                .build();
        commentMapper.update(comment);
    }

    @Override
    public void deleteComment(Long commentId) throws Exception {
        commentMapper.delete(commentId);
    }
}
