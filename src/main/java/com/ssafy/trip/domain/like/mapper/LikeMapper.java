package com.ssafy.trip.domain.like.mapper;

import com.ssafy.trip.domain.like.entity.Like;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface LikeMapper {

    void insert(Like like) throws SQLException;
    void delete(Long likeId) throws SQLException;
    int count(Long spotId) throws SQLException;

}
