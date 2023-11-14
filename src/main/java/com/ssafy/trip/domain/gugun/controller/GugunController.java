package com.ssafy.trip.domain.gugun.controller;

import com.ssafy.trip.domain.gugun.dto.GugunDto;
import com.ssafy.trip.domain.gugun.service.GugunService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gugun")
@RequiredArgsConstructor
public class GugunController {

    private final GugunService gugunService;

    @GetMapping
    public ResponseEntity<?> findAllBySidoCode(@RequestParam int sidoCode) throws Exception {
        List<GugunDto> list = gugunService.findAllBySidoCode(sidoCode);
        return ResponseEntity.ok(Response.success(list));
    }


}
