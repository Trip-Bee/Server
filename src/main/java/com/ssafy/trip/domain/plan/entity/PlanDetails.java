package com.ssafy.trip.domain.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetails {
    private Long id;
    // FK
    private Long planId;
    private Long spotId;
    private Long vehicleId;

    private int order;
    private Long cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlanDetails(Long id, Long planId, Long spotId, Long vehicleId, int order, Long cost, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.planId = planId;
        this.spotId = spotId;
        this.vehicleId = vehicleId;
        this.order = order;
        this.cost = cost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
