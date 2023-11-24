package com.ssafy.trip.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    // 검색 조건(nickname, title)
    private String key;
    // 검색 단어
    private String word;

}
