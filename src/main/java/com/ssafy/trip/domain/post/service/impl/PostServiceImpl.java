package com.ssafy.trip.domain.post.service.impl;

import com.ssafy.trip.domain.post.dto.ModifyPostRequestDto;
import com.ssafy.trip.domain.post.dto.PostResponseDto;
import com.ssafy.trip.domain.post.dto.WritePostRequestDto;
import com.ssafy.trip.domain.post.entity.Post;
import com.ssafy.trip.domain.post.mapper.PostMapper;
import com.ssafy.trip.domain.post.service.PostService;
import com.ssafy.trip.global.dto.PageRequest;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.dto.SearchRequest;
import com.ssafy.trip.global.util.PageUtil;
import com.ssafy.trip.global.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        postMapper.save(post);
    }

    @Override
    public PostResponseDto getPost(String category, Long postId) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("category", category);
        map.put("postId", postId.toString());
        return postMapper.findByIdAndCategory(map).toDto();
    }

    @Override
    public PageResponse getPosts(String category, Map<String, String> map) throws Exception {
        PageRequest pageRequest = new PageRequest(map.get("page"), map.get("size"));
        SearchRequest searchRequest = new SearchRequest(map.get("key"), map.get("word"));

        Map<String, String> param = new HashMap<>();
        param.putAll(PageUtil.getStartAndSize(pageRequest));
        param.putAll(SearchUtil.getKeyAndWord(searchRequest));
        param.put("category", convertCategory(category).toString());

        int size = Integer.parseInt(param.get("size"));
        int totalCount = postMapper.countByCategory(convertCategory(category).toString());
        int currentPage = Integer.parseInt(param.get("page"));
        int totalPage = (totalCount - 1) / Integer.parseInt(param.get("size")) + 1;

        List<PostResponseDto> list = postMapper.findAllByCategory(param)
                .stream().map(Post::toDto)
                .collect(Collectors.toList());

        return PageResponse.builder()
                .data(list)
                .size(size)
                .currentPage(currentPage)
                .totalPage(totalPage)
                .build();
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
