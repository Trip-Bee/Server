package com.ssafy.trip.domain.like.service;

public interface LikeService {

//    void addLike(LikeDto likeDto) throws Exception;
//    void deleteLike(Long likeId) throws Exception;
    int count(int spotId) throws Exception;

    void addOrDeleteLike(Long userId, Long spotId) throws Exception;

}
