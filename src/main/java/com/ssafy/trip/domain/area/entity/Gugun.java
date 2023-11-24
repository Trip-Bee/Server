package com.ssafy.trip.domain.area.entity;

import com.ssafy.trip.domain.area.dto.GugunDto;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Gugun {

    private int code;
    private String name;
    private int sidoCode;

    public GugunDto toDto() {
        return GugunDto.builder()
                .code(code)
                .name(name)
                .sidoCode(sidoCode)
                .build();
    }

}
