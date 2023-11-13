package com.ssafy.trip.domain.post.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ModifyPostRequestDto {

    private Long id;
    private Long writerId;
    private String title;
    private String content;

}
