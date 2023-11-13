package com.ssafy.trip.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteRequestDto {

    private Long writerId;
    private String title;
    private String content;

}
