package com.ssafy.trip.domain.plan.controller;

import com.ssafy.trip.domain.plan.dto.PlanRequestDto;
import com.ssafy.trip.domain.plan.service.PlanService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;


//    private Long writerId;
//    private String title;
//    private Long totalCost;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private int head_count;
//    private String theme;
//    private List<PlanRequestDto.PlanDetailsDto> planDetails;
    @PostMapping()
    public ResponseEntity addPlan(@RequestBody PlanRequestDto planRequestDto) {

        planService.addPlan(planRequestDto.getTheme(), planRequestDto.toPlan(), planRequestDto.toPlanDetail());

        return ResponseEntity.ok(Response.success());
    }

    @GetMapping("/{planId}")
    public ResponseEntity getPlan(@PathVariable Long planId) {

        return ResponseEntity.ok(Response.success());
    }

}
