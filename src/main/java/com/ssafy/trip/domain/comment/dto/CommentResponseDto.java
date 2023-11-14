package com.ssafy.trip.domain.comment.dto;

import com.ssafy.trip.domain.comment.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;
    private Long postId;
    private Long writerId;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
