package com.ssafy.trip.domain.spot.dto;

import com.ssafy.trip.global.dto.PageRequest;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotSearchRequestDto {

    private int sidoCode;
    private int gugunCode;
    private int typeId;
    private String query;
    private PageRequest pageRequest;

}
