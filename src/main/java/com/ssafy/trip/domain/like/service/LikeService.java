package com.ssafy.trip.domain.like.service;

import com.ssafy.trip.domain.like.dto.LikeDto;
import com.ssafy.trip.domain.like.entity.Like;

public interface LikeService {

    void addLike(LikeDto likeDto) throws Exception;
    void deleteLike(Long likeId) throws Exception;
    int count(Long spotId) throws Exception;

}
