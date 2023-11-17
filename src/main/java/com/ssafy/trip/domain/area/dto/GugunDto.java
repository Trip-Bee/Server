package com.ssafy.trip.domain.area.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GugunDto {

    private int code;
    private String name;
    private int sidoCode;


}
