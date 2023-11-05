package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.PostDto;
import com.ssafy.trip.domain.post.entity.Post;
import com.ssafy.trip.domain.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public void registerPost(PostDto postDto) throws Exception {
        Post post = postDto.toEntity();
        postMapper.insert(post);
    }

    @Override
    public PostDto getPost(Long postId) throws Exception {
        return postMapper.findById(postId).toDto();
    }

    @Override
    public List<PostDto> getPosts(Map<String, String> map) throws Exception {
        return postMapper.findAll(map)
                .stream().map(Post::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void modifyPost(PostDto postDto) throws Exception {
        postMapper.update(postDto.toEntity());
    }

    @Override
    public void updateHit(Long postId) throws Exception {
        postMapper.updateHit(postId);
    }

    @Override
    public void deletePost(Long postId) throws Exception {
        postMapper.deleteById(postId);
    }
}
