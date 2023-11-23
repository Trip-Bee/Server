package com.ssafy.trip.domain.spot.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotTop10Dto {
    private int spotId;
    private String title;
    private String addr;
    private String image;
    private Boolean isLike;
    private int likeCount;
}
