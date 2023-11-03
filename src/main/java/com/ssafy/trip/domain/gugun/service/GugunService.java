package com.ssafy.trip.domain.gugun.service;

import com.ssafy.trip.domain.gugun.dto.GugunDto;

import java.util.List;

public interface GugunService {

    List<GugunDto> findAllBySidoCode(int sidoCode) throws Exception;

}
