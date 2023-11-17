package com.ssafy.trip.domain.area.service;

import com.ssafy.trip.domain.area.dto.GugunDto;
import com.ssafy.trip.domain.area.dto.SidoDto;
import com.ssafy.trip.domain.area.entity.Gugun;
import com.ssafy.trip.domain.area.entity.Sido;
import com.ssafy.trip.domain.area.mapper.AreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaMapper areaMapper;

    @Override
    public List<SidoDto> getSidoList() throws Exception {
        return areaMapper.findAllSido()
                .stream().map(Sido::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GugunDto> getGugunList(int sidoCode) throws Exception {
        return areaMapper.findAllGugunBySidoCode(sidoCode)
                .stream().map(Gugun::toDto)
                .collect(Collectors.toList());

    }

}
