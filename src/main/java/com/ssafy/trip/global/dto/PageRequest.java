package com.ssafy.trip.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    // 현재 페이지
    private int page;
    // 페이지 크기
    private int size;

}
