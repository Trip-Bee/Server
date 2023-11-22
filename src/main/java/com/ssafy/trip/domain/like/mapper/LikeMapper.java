package com.ssafy.trip.domain.like.mapper;

import com.ssafy.trip.domain.like.entity.Like;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface LikeMapper {

    void save(Like like) throws SQLException;
    void delete(Long likeId) throws SQLException;
    int count(Long spotId) throws SQLException;
    Optional<Like> findByUserIdAndSpotId(Map<String, Long> map);

}
