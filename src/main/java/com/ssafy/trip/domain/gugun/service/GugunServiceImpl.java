package com.ssafy.trip.domain.gugun.service;

import com.ssafy.trip.domain.gugun.dto.GugunDto;
import com.ssafy.trip.domain.gugun.entity.Gugun;
import com.ssafy.trip.domain.gugun.mapper.GugunMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GugunServiceImpl implements GugunService {

    private final GugunMapper gugunMapper;

    @Override
    public List<GugunDto> findAllBySidoCode(int sidoCode) throws Exception {
        return gugunMapper.findAllBySidoCode(sidoCode)
                .stream().map(Gugun::toDto)
                .collect(Collectors.toList());

    }
}
