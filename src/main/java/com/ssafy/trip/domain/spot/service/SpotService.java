package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.dto.PageResponse;

import java.util.List;
import java.util.Map;

public interface SpotService {

    List<SpotTypeDto> getSpotTypeList() throws Exception;
    PageResponse search(Map<String, String> map, LoginUserDto loginUserDto) throws Exception;
    SpotDto getSpot(int spotId) throws Exception;

}
