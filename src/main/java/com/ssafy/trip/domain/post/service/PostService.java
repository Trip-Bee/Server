package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;

import java.util.List;

public interface PostService {

    void writePost(String category, WritePostRequestDto writePostRequestDto) throws Exception;

    PostResponseDto getPost(Long postId) throws Exception;

    List<PostResponseDto> getPosts(String category) throws Exception;

    void modifyPost(String category, ModifyPostRequestDto modifyPostRequestDto) throws Exception;

    void updateHit(Long postId) throws Exception;

    void deletePost(Long postId) throws Exception;

}
