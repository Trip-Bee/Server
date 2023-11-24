package com.ssafy.trip.domain.plan.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailsDto {
    // User
    private String nickname;

    // Plan
    private String planTitle;
    private Long totalCost;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headCount;
    private Long hit;

    // Theme
    private String theme;

    // PlanDetails, Spot, Vehicle
    private List<DetailsDto> details;

    public void addDetails(List<DetailsDto> details) {
        this.details = details;
    }
}
