package com.ssafy.trip.domain.post.mapper;

import com.ssafy.trip.domain.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    void insert(Post post) throws SQLException;

    Post findById(Long postId) throws SQLException;

    List<Post> findAll(Map<String, String> map) throws SQLException;

    void update(Post post) throws SQLException;

    void updateHit(Long postId) throws SQLException;

    void deleteById(Long postId) throws SQLException;


}
