package com.ssafy.trip.domain.comment.mapper;

import com.ssafy.trip.domain.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {

    void insert(Comment comment) throws SQLException;
    List<Comment> getComments(Long postId) throws  SQLException;
    void update(Comment comment) throws SQLException;
    void delete(Long commentId) throws SQLException;

}
