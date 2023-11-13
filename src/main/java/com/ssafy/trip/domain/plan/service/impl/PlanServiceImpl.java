package com.ssafy.trip.domain.plan.service.impl;

import com.ssafy.trip.domain.plan.mapper.PlanMapper;
import com.ssafy.trip.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanMapper planMapper;


}
