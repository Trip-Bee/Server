package com.ssafy.trip.domain.comment.service;

import com.ssafy.trip.domain.comment.dto.CommentDto;
import com.ssafy.trip.domain.comment.entity.Comment;
import com.ssafy.trip.domain.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;

    @Override
    public void registerComment(CommentDto commentDto) throws Exception {
        Comment comment = commentDto.toEntity();
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentDto> getComments(Long postId) throws Exception {
        return commentMapper.getComments(postId)
                .stream().map(Comment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void modifyComment(CommentDto commentDto) throws Exception {
        commentMapper.update(commentDto.toEntity());
    }

    @Override
    public void deleteComment(Long commentId) throws Exception {
        commentMapper.delete(commentId);
    }
}
