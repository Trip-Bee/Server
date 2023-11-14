package com.ssafy.trip.domain.comment.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ModifyCommentRequestDto {

    private Long id;
    private Long writerId;
    private Long postId;
    private String content;

}
