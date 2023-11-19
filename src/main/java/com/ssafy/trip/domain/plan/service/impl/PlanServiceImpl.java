package com.ssafy.trip.domain.plan.service.impl;

import com.ssafy.trip.domain.plan.dto.DetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanDetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanDto;
import com.ssafy.trip.domain.plan.entity.Plan;
import com.ssafy.trip.domain.plan.entity.PlanDetails;
import com.ssafy.trip.domain.plan.entity.Theme;
import com.ssafy.trip.domain.plan.mapper.PlanMapper;
import com.ssafy.trip.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanMapper planMapper;

    @Override
    public void addPlan(String themeName, Plan plan, List<PlanDetails> details) {
        Long themeId = saveTheme(themeName);
        Long planId = savePlan(plan, themeId);

        details.stream().forEach(detail -> detail.updatePlan(planId));

        for (PlanDetails detail : details) {
            log.debug("detail : {}", detail);
        }

        savePlanDetails(details);
    }

    private void savePlanDetails(List<PlanDetails> planDetails) {
        Map<String, List<PlanDetails>> map = new HashMap<>();
        map.put("list", planDetails);

        planMapper.savePlanDetails(map);
    }

    private Long saveTheme(String themeName) {
        Optional<Theme> findTheme = planMapper.findByName(themeName);

        if (findTheme.isPresent()) {
            return findTheme.get().getId();
        }

        Theme theme = new Theme(themeName);
        // 없으면 save
        planMapper.saveTheme(theme);
        log.debug("============ Theme id : {}", theme.getId());

        return theme.getId();
    }

    private Long savePlan(Plan plan, Long themeId) {
        plan.addTheme(themeId);

        planMapper.savePlan(plan);
        log.debug("=========== plan id : {}", plan.getId());
        log.debug("============ start: {}, end: {}", plan.getStartDate(), plan.getEndDate());

        return plan.getId();
    }

    @Override
    public List<PlanDto> findPlans() {
        List<PlanDto> plans = planMapper.findPlans();

        return plans;
    }

    @Override
    public PlanDetailsDto getPlan(Long planId) {
        PlanDetailsDto planDetailsDto = planMapper.findPlanById(planId);

        List<DetailsDto> details = planMapper.findPlanDetailsByPlanId(planId);

        for (DetailsDto detail : details) {
            log.debug("============= detail {}", detail);
        }

        planDetailsDto.addDetails(details);

        planMapper.updateHit(planId);
        
        return planDetailsDto;
    }

    @Override
    public void delete(Long planId) {
        planMapper.deleteById(planId);
    }
}
