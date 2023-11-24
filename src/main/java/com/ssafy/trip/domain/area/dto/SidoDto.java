package com.ssafy.trip.domain.area.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SidoDto {

    private int code;
    private String name;


}
