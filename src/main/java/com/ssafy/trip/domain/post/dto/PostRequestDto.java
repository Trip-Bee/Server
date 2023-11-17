package com.ssafy.trip.domain.post.dto;

import com.ssafy.trip.global.dto.PageRequest;
import com.ssafy.trip.global.dto.SearchRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {

    private SearchRequest searchRequest;
    private PageRequest pageRequest;

}
