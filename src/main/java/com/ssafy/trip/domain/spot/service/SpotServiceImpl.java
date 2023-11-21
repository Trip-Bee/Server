package com.ssafy.trip.domain.spot.service;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.entity.Spot;
import com.ssafy.trip.domain.spot.entity.SpotType;
import com.ssafy.trip.domain.spot.mapper.SpotMapper;
import com.ssafy.trip.global.dto.PageRequest;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.util.PageUtil;
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
    public List<SpotTypeDto> getSpotTypeList() throws Exception {
        return spotMapper.findAllSpotType()
                .stream().map(SpotType::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse search(Map<String, String> map) throws Exception {
        PageRequest pageRequest = new PageRequest(map.get("page"), map.get("size"));
        map.putAll(PageUtil.getStartAndSize(pageRequest));

        int size = Integer.parseInt(map.get("size"));
        int totalCount = spotMapper.countBySearch(map);
        int currentPage = Integer.parseInt(map.get("page"));
        int totalPage = (totalCount - 1) / Integer.parseInt(map.get("size")) + 1;

        List<SpotDto> list = spotMapper.search(map)
                .stream().map(Spot::toDto)
                .collect(Collectors.toList());

        return PageResponse.<List<SpotDto>>builder()
                .data(list)
                .size(size)
                .currentPage(currentPage)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public SpotDto getSpot(int spotId) throws Exception {
        return spotMapper.findBySpotId(spotId).toDto();
    }
}
