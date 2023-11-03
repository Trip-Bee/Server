package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SpotDto;

import java.util.List;
import java.util.Map;

public interface SpotService {

    List<SpotDto> search(Map<String, String> map) throws Exception;


}
