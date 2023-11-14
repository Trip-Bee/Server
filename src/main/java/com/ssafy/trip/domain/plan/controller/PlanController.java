package com.ssafy.trip.domain.plan.controller;

import com.ssafy.trip.domain.plan.dto.PlanDto;
import com.ssafy.trip.domain.plan.dto.PlanRequestDto;
import com.ssafy.trip.domain.plan.service.PlanService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity addPlan(@RequestBody PlanRequestDto planRequestDto) {

        planService.addPlan(planRequestDto.getTheme(), planRequestDto.toPlan(), planRequestDto.toPlanDetail());

        return ResponseEntity.ok(Response.success());
    }

    // TODO 페이징 처리
    @GetMapping()
    public ResponseEntity getPlans() {

        List<PlanDto> plans = planService.findPlans();

        return ResponseEntity.ok(Response.success(plans));
    }


    @GetMapping("/{planId}")
    public ResponseEntity getPlan(@PathVariable Long planId) {

        // join
        // return
        // plan >> 제목, 여행비용, 시작일, 종료일, 여행인원, 조회수
        // planDetails >> 순서, 비용
        // theme >> name
        // vehicle >> name
        // spot >> 여행지명, 사진
        // user >> nickname

        return ResponseEntity.ok(Response.success());
    }

}
