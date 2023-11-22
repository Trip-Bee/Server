package com.ssafy.trip.domain.spot.controller;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.service.SpotService;
import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity search(@RequestParam Map<String, String> map,
                                 @AuthenticationPrincipal LoginUserDto loginUserDto) throws Exception {
        PageResponse pageResponse = spotService.search(map, loginUserDto);
        return ResponseEntity.ok(Response.success(pageResponse));
    }

    @GetMapping("/{spotId}")
    public ResponseEntity getSpot(@PathVariable int spotId) throws Exception {
        SpotDto spotDto = spotService.getSpot(spotId);
        return ResponseEntity.ok(Response.success(spotDto));
    }

}
