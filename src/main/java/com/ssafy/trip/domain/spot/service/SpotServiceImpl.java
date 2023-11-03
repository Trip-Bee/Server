package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.mapper.SpotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotMapper spotMapper;

    @Override
    public List<SpotDto> search(Map<String, String> map) throws Exception {
        return spotMapper.search(map)
                .stream().map(Spot::toDto)
                .collect(Collectors.toList());
    }
}
