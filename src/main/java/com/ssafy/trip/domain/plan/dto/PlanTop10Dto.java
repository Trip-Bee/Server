package com.ssafy.trip.domain.plan.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanTop10Dto {
    private Long planId;
    private String title;
    private Long hit;
}
