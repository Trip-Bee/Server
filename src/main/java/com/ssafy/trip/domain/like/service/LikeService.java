package com.ssafy.trip.domain.like.service;

import com.ssafy.trip.domain.like.dto.LikeResponse;

public interface LikeService {

//    void addLike(LikeDto likeDto) throws Exception;
//    void deleteLike(Long likeId) throws Exception;
    int count(int spotId) throws Exception;

    // 반환값은 적용후 좋아요 여부
    LikeResponse addOrDeleteLike(Long userId, int spotId) throws Exception;

}
