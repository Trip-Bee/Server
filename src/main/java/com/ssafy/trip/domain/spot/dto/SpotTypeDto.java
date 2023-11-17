package com.ssafy.trip.domain.spot.dto;

import com.ssafy.trip.domain.spot.entity.SpotType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotTypeDto {

    private int id;
    private String name;

    public SpotType toEntity() {
        return SpotType.builder()
                .id(id)
                .name(name)
                .build();
    }

}