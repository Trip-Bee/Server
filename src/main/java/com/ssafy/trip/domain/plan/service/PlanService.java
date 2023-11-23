package com.ssafy.trip.domain.plan.service;

import com.ssafy.trip.domain.plan.dto.PlanDetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanTop10Dto;
import com.ssafy.trip.domain.plan.entity.Plan;
import com.ssafy.trip.domain.plan.entity.PlanDetails;
import com.ssafy.trip.global.dto.PageResponse;

import java.util.List;
import java.util.Map;

public interface PlanService {

    void addPlan(String themeName, Plan plan, List<PlanDetails> details);
    PageResponse findPlans(Map<String, String> map);
    PlanDetailsDto getPlan(Long planId);

    List<PlanTop10Dto> getPlansTop10();
    void delete(Long planId);
}
