package com.ssafy.trip.domain.spot_type.entity;

import com.ssafy.trip.domain.spot_type.dto.SpotTypeDto;
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
