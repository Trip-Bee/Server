package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SearchRequestDto;
import com.ssafy.trip.domain.spot.dto.SearchResponseDto;

import java.util.List;
import java.util.Map;

public interface SpotService {

    List<SearchResponseDto> search(SearchRequestDto searchRequestDto) throws Exception;


}
