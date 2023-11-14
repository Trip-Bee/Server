package com.ssafy.trip.domain.plan.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlanDetails {
    private Long id;
    // FK
    private Long planId;
    private int spotId;
    private Long vehicleId;
    private int order;
    private Long cost;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updatePlan(Long planId) {
        this.planId = planId;
    }

}
