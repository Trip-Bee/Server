package com.ssafy.trip.domain.sido.service;

import com.ssafy.trip.domain.sido.dto.SidoDto;

import java.util.List;

public interface SidoService {

    List<SidoDto> findAll() throws Exception;

}
