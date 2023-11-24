package com.ssafy.trip.domain.plan.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private Long planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private Long writerId;
    private String nickname;
    private Long hit;
    private LocalDateTime createdAt;
}
