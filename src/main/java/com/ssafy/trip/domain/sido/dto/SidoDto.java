package com.ssafy.trip.domain.sido.dto;

import com.ssafy.trip.domain.sido.entity.Sido;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SidoDto {

    private int code;
    private String name;

    public Sido toEntity() {
        return Sido.builder()
                .code(code)
                .name(name)
                .build();
    }

}
