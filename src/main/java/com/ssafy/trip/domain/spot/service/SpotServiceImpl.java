package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SearchRequestDto;
import com.ssafy.trip.domain.spot.dto.SearchResponseDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.entity.SpotType;
import com.ssafy.trip.domain.spot.mapper.SpotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotMapper spotMapper;

    @Override
    public List<SpotTypeDto> getSpotTypeList() throws Exception {
        return spotMapper.findAllSpotType()
                .stream().map(SpotType::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchResponseDto> search(SearchRequestDto searchRequestDto) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("sidoCode", String.valueOf(searchRequestDto.getSidoCode()));
        map.put("gugunCode", String.valueOf(searchRequestDto.getGugunCode()));
        map.put("typeId", String.valueOf(searchRequestDto.getTypeId()));
        map.put("query", searchRequestDto.getQuery());
        return spotMapper.search(map)
                .stream().map(Spot::toDto)
                .collect(Collectors.toList());
    }
}
