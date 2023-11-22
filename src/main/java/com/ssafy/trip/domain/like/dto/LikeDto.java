package com.ssafy.trip.domain.like.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LikeDto {

    private Long userId;
    private Long spotId;
}
