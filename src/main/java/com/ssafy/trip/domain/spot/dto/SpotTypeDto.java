package com.ssafy.trip.domain.spot.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotTypeDto {

    private int id;
    private String name;

}
