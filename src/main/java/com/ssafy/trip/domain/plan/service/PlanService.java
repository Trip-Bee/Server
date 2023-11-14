package com.ssafy.trip.domain.plan.service;

import com.ssafy.trip.domain.plan.dto.PlanDetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanDto;
import com.ssafy.trip.domain.plan.entity.Plan;
import com.ssafy.trip.domain.plan.entity.PlanDetails;

import java.util.List;

public interface PlanService {

    void addPlan(String themeName, Plan plan, List<PlanDetails> details);
    List<PlanDto> findPlans();

    PlanDetailsDto getPlan(Long planId);
}
