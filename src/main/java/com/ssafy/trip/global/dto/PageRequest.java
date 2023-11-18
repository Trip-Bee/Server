package com.ssafy.trip.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    // 현재 페이지
    private String page;
    // 페이지 크기
    private String size;

}
