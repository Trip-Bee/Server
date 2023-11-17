package com.ssafy.trip.global.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private T data;
    private int currentPage;
    private int totalPage;

}
