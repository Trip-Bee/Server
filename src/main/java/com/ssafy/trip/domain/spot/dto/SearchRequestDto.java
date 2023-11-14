package com.ssafy.trip.domain.spot.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SearchRequestDto {

    private int sidoCode;
    private int gugunCode;
    private int typeId;
    private String query;

}
