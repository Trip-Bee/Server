package com.ssafy.trip.domain.area.controller;

import com.ssafy.trip.domain.area.dto.GugunDto;
import com.ssafy.trip.domain.area.dto.SidoDto;
import com.ssafy.trip.domain.area.service.AreaService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/area")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/sido")
    public ResponseEntity getSidoList() throws Exception {
        List<SidoDto> list = areaService.getSidoList();
        return ResponseEntity.ok(Response.success(list));
    }

    @GetMapping("/gugun")
    public ResponseEntity getGugunList(@RequestParam int sidoCode) throws Exception {
        List<GugunDto> list = areaService.getGugunList(sidoCode);
        return ResponseEntity.ok(Response.success(list));
    }


}
