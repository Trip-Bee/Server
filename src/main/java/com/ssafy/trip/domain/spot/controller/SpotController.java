package com.ssafy.trip.domain.spot.controller;

import com.ssafy.trip.domain.spot.dto.SpotSearchRequestDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.service.SpotService;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/type")
    public ResponseEntity getSpotTypeList() throws Exception {
        List<SpotTypeDto> list = spotService.getSpotTypeList();
        return ResponseEntity.ok(Response.success(list));
    }

    @GetMapping
    public ResponseEntity search(@RequestBody SpotSearchRequestDto spotSearchRequestDto) throws Exception {
        PageResponse pageResponse = spotService.search(spotSearchRequestDto);
        return ResponseEntity.ok(Response.success(pageResponse));
    }

}
