package com.ssafy.trip.domain.comment.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class WriteCommentRequestDto {

    private Long writerId;
    private Long postId;
    private String content;

}
