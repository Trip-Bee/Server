package com.ssafy.trip.domain.sido.entity;

import com.ssafy.trip.domain.sido.dto.SidoDto;
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
