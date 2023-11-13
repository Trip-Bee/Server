package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.ModifyRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WriteRequestDto;

import java.util.List;
import java.util.Map;

public interface PostService {

    void writePost(String category, WriteRequestDto writeRequestDto) throws Exception;

    PostResponseDto getPost(Long postId) throws Exception;

    List<PostResponseDto> getPosts(String category) throws Exception;

    void modifyPost(String category, ModifyRequestDto modifyRequestDto) throws Exception;

    void updateHit(Long postId) throws Exception;

    void deletePost(Long postId) throws Exception;

}
