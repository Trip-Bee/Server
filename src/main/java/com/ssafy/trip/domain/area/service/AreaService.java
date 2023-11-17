package com.ssafy.trip.domain.area.service;

import com.ssafy.trip.domain.area.dto.GugunDto;
import com.ssafy.trip.domain.area.dto.SidoDto;

import java.util.List;

public interface AreaService {

    List<SidoDto> getSidoList() throws Exception;
    List<GugunDto> getGugunList(int sidoCode) throws Exception;

}
