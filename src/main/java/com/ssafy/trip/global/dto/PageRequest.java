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
    private int page;
    // 페이지 크기
    private int size;

}
