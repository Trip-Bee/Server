package com.ssafy.trip.domain.plan.controller;

import com.ssafy.trip.domain.plan.service.PlanService;
import com.ssafy.trip.global.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    @PostMapping()
    public ResponseEntity addPlan() {

        return ResponseEntity.ok(Response.success());
    }
}
