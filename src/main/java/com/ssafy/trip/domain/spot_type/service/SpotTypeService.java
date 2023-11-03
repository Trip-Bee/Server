package com.ssafy.trip.domain.spot_type.service;

import com.ssafy.trip.domain.spot_type.dto.SpotTypeDto;

import java.util.List;

public interface SpotTypeService {

    List<SpotTypeDto> findAll() throws Exception;

}
