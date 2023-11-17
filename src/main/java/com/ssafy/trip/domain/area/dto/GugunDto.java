package com.ssafy.trip.domain.area.dto;

import com.ssafy.trip.domain.area.entity.Gugun;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GugunDto {

    private int code;
    private String name;
    private int sidoCode;

    public Gugun toEntity() {
        return Gugun.builder()
                .code(code)
                .name(name)
                .sidoCode(sidoCode)
                .build();
    }

}
