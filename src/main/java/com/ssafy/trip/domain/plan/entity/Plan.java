package com.ssafy.trip.domain.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private Long id;
    // FK
    private Long userId;
    private Long theme_id;

    private String title;
    private Long totalCost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int head_count;
    private Long hit;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
