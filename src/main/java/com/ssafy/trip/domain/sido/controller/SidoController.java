package com.ssafy.trip.domain.sido.controller;

import com.ssafy.trip.domain.sido.dto.SidoDto;
import com.ssafy.trip.domain.sido.service.SidoService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sido")
@RequiredArgsConstructor
public class SidoController {

    private final SidoService sidoService;

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        List<SidoDto> list = sidoService.findAll();
        return ResponseEntity.ok(Response.success(list));
    }

}
