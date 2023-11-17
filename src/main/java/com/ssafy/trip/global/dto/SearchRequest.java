package com.ssafy.trip.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    // 검색 조건(nickname, title)
    private String key;
    // 검색 단어
    private String word;

}
