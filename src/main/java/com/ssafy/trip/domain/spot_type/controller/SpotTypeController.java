package com.ssafy.trip.domain.spot_type.controller;

import com.ssafy.trip.domain.spot_type.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot_type.service.SpotTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spot-type")
@RequiredArgsConstructor
public class SpotTypeController {

    private final SpotTypeService spotTypeService;

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        List<SpotTypeDto> list = spotTypeService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
