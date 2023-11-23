package com.ssafy.trip.domain.spot.controller;

import com.ssafy.trip.domain.spot.dto.SpotDto;
import com.ssafy.trip.domain.spot.dto.SpotTop10Dto;
import com.ssafy.trip.domain.spot.dto.SpotTypeDto;
import com.ssafy.trip.domain.spot.service.SpotService;
import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.dto.PageResponse;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
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

        log.debug("============== search map {}", map);
        PageResponse pageResponse = spotService.search(map, loginUserDto);
        return ResponseEntity.ok(Response.success(pageResponse));
    }

    @GetMapping("/{spotId}")
    public ResponseEntity getSpot(@PathVariable int spotId,
                                  @AuthenticationPrincipal LoginUserDto loginUserDto) throws Exception {
        SpotDto spotDto = spotService.getSpot(spotId, loginUserDto);
        return ResponseEntity.ok(Response.success(spotDto));
    }

    @GetMapping("/top10")
    public ResponseEntity getSpotTop10(@AuthenticationPrincipal LoginUserDto loginUserDto) throws Exception {
        // 타이틀, 주소 + 우편번호, 이미지, spotId, 좋아요여부, 좋아요개수
        List<SpotTop10Dto> spotTop10 = spotService.getSpotTop10(loginUserDto);
        return ResponseEntity.ok(Response.success(spotTop10));
    }
}
