package com.ssafy.trip.domain.spot.dto;

import com.ssafy.trip.domain.spot.entity.Spot;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotDto {

    private int id;
    private int typeId;
    private String title;
    private String addr;
    private String tel;
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long sidoCode;
    private Long gugunCode;
    private String overview;
    private Boolean isLike;
    private int likeCount;
}
