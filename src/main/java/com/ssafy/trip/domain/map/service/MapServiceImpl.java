package com.ssafy.trip.domain.map.service;

import com.ssafy.treep.domain.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {
    private final MapMapper mapMapper;
}
