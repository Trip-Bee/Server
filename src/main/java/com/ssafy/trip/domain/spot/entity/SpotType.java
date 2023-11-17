package com.ssafy.trip.domain.spot.entity;

import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotType {

    private int id;
    private String name;

    public SpotTypeDto toDto() {
        return SpotTypeDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
