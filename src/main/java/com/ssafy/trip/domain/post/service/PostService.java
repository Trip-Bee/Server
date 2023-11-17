package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.global.dto.PageResponse;

public interface PostService {

    void writePost(String category, WritePostRequestDto writePostRequestDto) throws Exception;

    PostResponseDto getPost(Long postId) throws Exception;

    PageResponse getPosts(String category, PostRequestDto postRequestDto) throws Exception;

    void modifyPost(String category, ModifyPostRequestDto modifyPostRequestDto) throws Exception;

    void updateHit(Long postId) throws Exception;

    void deletePost(Long postId) throws Exception;

}
