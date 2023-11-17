package com.ssafy.trip.domain.area.entity;

import com.ssafy.trip.domain.area.dto.SidoDto;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Sido {

    private int code;
    private String name;

    public SidoDto toDto() {
        return SidoDto.builder()
                .code(code)
                .name(name)
                .build();
    }

}
