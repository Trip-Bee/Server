package com.ssafy.trip.domain.plan.mapper;

import com.ssafy.trip.domain.plan.dto.PlanDto;
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
    List<PlanDto> findPlans();
    void saveTheme(Theme theme);
    void savePlan(Plan plan);
    void savePlanDetails(Map<String, List<PlanDetails>> planDetails);
}
