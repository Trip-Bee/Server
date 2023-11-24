package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.global.dto.PageResponse;

import java.util.Map;

public interface PostService {

    void writePost(String category, WritePostRequestDto writePostRequestDto) throws Exception;

    PostResponseDto getPost(String category, Long postId) throws Exception;

    PageResponse getPosts(String category, Map<String, String> map) throws Exception;

    void modifyPost(String category, ModifyPostRequestDto modifyPostRequestDto) throws Exception;

    void updateHit(Long postId) throws Exception;

    void deletePost(Long postId) throws Exception;

}
