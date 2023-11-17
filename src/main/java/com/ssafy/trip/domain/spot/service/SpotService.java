package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SpotSearchRequestDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.global.dto.PageResponse;

import java.util.List;

public interface SpotService {

    List<SpotTypeDto> getSpotTypeList() throws Exception;
    PageResponse search(SpotSearchRequestDto spotSearchRequestDto) throws Exception;


}
