package com.ssafy.trip.domain.sido.service;

import com.ssafy.trip.domain.sido.dto.SidoDto;
import com.ssafy.trip.domain.sido.entity.Sido;
import com.ssafy.trip.domain.sido.mapper.SidoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SidoServiceImpl implements SidoService{

    private final SidoMapper sidoMapper;

    @Override
    public List<SidoDto> findAll() throws Exception {
        System.out.println(sidoMapper.findAll());
        return sidoMapper.findAll()
                .stream().map(Sido::toDto)
                .collect(Collectors.toList());
    }
}
