package com.ssafy.trip.domain.comment.dto;

import com.ssafy.trip.domain.comment.entity.Comment;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String content;
    private Long postId;
    private Long writerId;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .content(content)
                .postId(postId)
                .writerId(writerId)
                .build();
    }

}
