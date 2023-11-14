package com.ssafy.trip.domain.post.service;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.domain.post.entity.Post;
import com.ssafy.trip.domain.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public void writePost(String category, WritePostRequestDto writePostRequestDto) throws Exception {
        Post post = Post.builder()
                .title(writePostRequestDto.getTitle())
                .content(writePostRequestDto.getContent())
                .category(convertCategory(category))
                .writerId(writePostRequestDto.getWriterId())
                .build();
        postMapper.insert(post);
    }

    @Override
    public PostResponseDto getPost(Long postId) throws Exception {
        return postMapper.findById(postId).toDto();
    }

    @Override
    public List<PostResponseDto> getPosts(String category) throws Exception {
        return postMapper.findAllByCategory(convertCategory(category).toString())
                .stream().map(Post::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void modifyPost(String category, ModifyPostRequestDto modifyPostRequestDto) throws Exception {
        Post post = Post.builder()
                .id(modifyPostRequestDto.getId())
                .title(modifyPostRequestDto.getTitle())
                .content(modifyPostRequestDto.getContent())
                .category(convertCategory(category))
                .writerId(modifyPostRequestDto.getWriterId())
                .build();
        postMapper.update(post);
    }

    @Override
    public void updateHit(Long postId) throws Exception {
        postMapper.updateHit(postId);
    }

    @Override
    public void deletePost(Long postId) throws Exception {
        postMapper.deleteById(postId);
    }

    private Post.Category convertCategory(String category) {
        if (category.equals("qna")) return Post.Category.QnA;
        return Post.Category.valueOf(category.toUpperCase());
    }

}
