package com.ssafy.trip.domain.post.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class WritePostRequestDto {

    private Long writerId;
    private String title;
    private String content;

}
