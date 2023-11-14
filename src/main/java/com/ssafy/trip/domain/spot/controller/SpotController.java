package com.ssafy.trip.domain.spot.controller;

import com.ssafy.trip.domain.spot.dto.SearchRequestDto;
import com.ssafy.trip.domain.spot.dto.SearchResponseDto;
import com.ssafy.trip.domain.spot.service.SpotService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    public ResponseEntity<?> search(@RequestBody SearchRequestDto searchRequestDto) throws Exception {
        List<SearchResponseDto> list = spotService.search(searchRequestDto);
        return ResponseEntity.ok(Response.success(list));
    }

}
