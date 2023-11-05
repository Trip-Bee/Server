package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {

    void registerPost(PostDto postDto) throws Exception;

    PostDto getPost(Long postId) throws Exception;

    List<PostDto> getPosts(Map<String, String> map) throws Exception;

    void modifyPost(PostDto postDto) throws Exception;

    void updateHit(Long postId) throws Exception;

    void deletePost(Long postId) throws Exception;

}
