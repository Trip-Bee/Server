package com.ssafy.trip.domain.like.service;

public interface LikeService {

//    void addLike(LikeDto likeDto) throws Exception;
//    void deleteLike(Long likeId) throws Exception;
    int count(Long spotId) throws Exception;

    void addOrDeleteLike(Long userId, Long spotId) throws Exception;

}
