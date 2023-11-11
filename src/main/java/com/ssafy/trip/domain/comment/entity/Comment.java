package com.ssafy.trip.domain.comment.entity;

import com.ssafy.trip.domain.comment.dto.CommentDto;
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

    public CommentDto toDto() {
        return CommentDto.builder()
                .id(id)
                .content(content)
                .postId(postId)
                .writerId(writerId)
                .build();
    }

}
