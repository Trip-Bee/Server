package com.ssafy.trip.domain.comment.entity;

import com.ssafy.trip.domain.comment.dto.CommentResponseDto;
import com.ssafy.trip.global.entity.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    private Long id;
    private String content;
    private Long postId;
    private Long writerId;
    private String nickname;

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
                .id(id)
                .content(content)
                .postId(postId)
                .writerId(writerId)
                .nickname(nickname)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

}
