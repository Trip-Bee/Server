package com.ssafy.trip.domain.plan.mapper;

import com.ssafy.trip.domain.plan.dto.DetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanDetailsDto;
import com.ssafy.trip.domain.plan.dto.PlanDto;
import com.ssafy.trip.domain.plan.dto.PlanTop10Dto;
import com.ssafy.trip.domain.plan.entity.Plan;
import com.ssafy.trip.domain.plan.entity.PlanDetails;
import com.ssafy.trip.domain.plan.entity.Theme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface PlanMapper {
    Optional<Theme> findByName(String name);
    List<PlanDto> findPlans(Map<String, String> param);
    void saveTheme(Theme theme);
    void savePlan(Plan plan);
    void savePlanDetails(Map<String, List<PlanDetails>> planDetails);

    PlanDetailsDto findPlanById(Long id);

    List<DetailsDto> findPlanDetailsByPlanId(Long planId);

    List<PlanTop10Dto> findPlansTop10();

    int countPlans();

    void updateHit(Long Id);

    void deleteById(Long id);
}
