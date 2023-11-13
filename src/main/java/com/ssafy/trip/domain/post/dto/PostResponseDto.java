package com.ssafy.trip.domain.post.dto;

import com.ssafy.trip.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostResponseDto {

    private Long id;
    private Long writerId;
    private String nickname;
    private String title;
    private Post.Category category;
    private String content;
    private Long hit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
