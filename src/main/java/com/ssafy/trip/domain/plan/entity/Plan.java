package com.ssafy.trip.domain.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private Long id;
    // FK
    private Long writerId;
    private Long themeId;

    private String title;
    private Long totalCost;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headCount;
    private Long hit;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addTheme(Long themeId) {
        this.themeId = themeId;
    }
}
