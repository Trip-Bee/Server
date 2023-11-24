package com.ssafy.trip.domain.post.dto;

import com.ssafy.trip.domain.post.entity.Post;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private Long writerId;
    private String nickname;
    private String title;
    private Post.Category category;
    private String content;
    private Long hit;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;

}
