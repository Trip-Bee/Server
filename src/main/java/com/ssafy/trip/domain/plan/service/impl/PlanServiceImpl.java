package com.ssafy.trip.domain.plan.service.impl;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        // planDetails 의 연결된 모든 값들을 가져와야 하기에 right outer join planDetails
        // User, Plan, Theme 의 경우 1개씩 연결된다. 하지만 PlanDetails 가 여러개 연결되기에
        // PlanDetails와 붙어있는 Spot, Vehicle 도 여러개 값이 나온다.
        // 즉, right outer join 할 경우
        // User, Plan, Theme 의 값들이 planDetails, Spot, Vehicle 에 의해 중복해서 나온다.

        // User, Plan, Theme 조회
        // planDetails, Spot, Vehicle 를 Plan의 id값으로 PlanDetails.plan_id = id; 형태로 조건 걸고
        // 3개 테이블은 inner join?

        // 1. User, Plan, Theme 조회
        // 2. planDetails, Spot, Vehicle 를 Plan의 id값으로 PlanDetails.plan_id = id; 형태로 조건 걸고 3개 테이블은 inner join
        // 3. 1, 2 엮기 >> DTO 하나 만들고 해당 DTO에 User, Plan, Theme 의 데이터 넣고 List<planDetails>


//        mybatis inner class 조회 >> TodoDto 클래스의 inner class Response
//        com.todo.blog.model.TodoDto$Response
//        planMapper.findUserAndPlanAndTheme(planId);

//        return planMapper.findPlanDtoById(planId);

        PlanDetailsDto planDetailsDto = planMapper.findPlanById(planId);

        // TODO mapper의 resultMap에서 이름 매칭시킬때 만약 User의 name, Theme의 name이란게 있을때 name이 이름만 중복되는데 이럴때 어떻게 처리? alias?

//        조회수 hit 증가
        return null;
    }
}
