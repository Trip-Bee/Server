package com.ssafy.trip.domain.post.mapper;

import com.ssafy.trip.domain.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    void save(Post post) throws SQLException;

    Post findByIdAndCategory(Map<String, String> map) throws SQLException;

    List<Post> findAllByCategory(Map<String, String> map) throws SQLException;

    int countByCategory(String category) throws SQLException;

    void update(Post post) throws SQLException;

    void updateHit(Long postId) throws SQLException;

    void deleteById(Long postId) throws SQLException;


}
