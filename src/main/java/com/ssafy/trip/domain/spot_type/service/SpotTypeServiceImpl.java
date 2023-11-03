package com.ssafy.trip.domain.spot_type.service;

import com.ssafy.trip.domain.spot_type.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot_type.entity.SpotType;
import com.ssafy.trip.domain.spot_type.mapper.SpotTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotTypeServiceImpl implements SpotTypeService {

    private final SpotTypeMapper spotTypeMapper;

    @Override
    public List<SpotTypeDto> findAll() throws Exception {
        return spotTypeMapper.findAll()
                .stream().map(SpotType::toDto)
                .collect(Collectors.toList());
    }
}
