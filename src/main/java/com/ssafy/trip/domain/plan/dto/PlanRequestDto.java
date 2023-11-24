package com.ssafy.trip.domain.plan.dto;

import com.ssafy.trip.domain.plan.entity.Plan;
import com.ssafy.trip.domain.plan.entity.PlanDetails;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PlanRequestDto {
    private Long writerId;
    private String title;
    private long totalCost;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headCount;
    private String theme;
    private List<PlanDetailsDto> planDetails;

    public Plan toPlan() {
        return Plan.builder()
                .writerId(writerId)
                .title(title)
                .totalCost(totalCost)
                .startDate(startDate)
                .endDate(endDate)
                .headCount(headCount)
                .build();
    }

    public List<PlanDetails> toPlanDetail() {
        return this.planDetails.stream().map(planDetail -> planDetail.toEntity()).collect(Collectors.toList());
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PlanDetailsDto {
        private int spotId;
        private Long vehicleId;
        private int order;
        private int dateOrder;
        private Long cost;

        public PlanDetails toEntity() {
            return PlanDetails.builder()
                    .spotId(spotId)
                    .vehicleId(vehicleId)
                    .order(order)
                    .dateOrder(dateOrder)
                    .cost(cost)
                    .build();
        }
    }
}
