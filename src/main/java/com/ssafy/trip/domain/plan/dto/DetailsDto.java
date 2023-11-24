package com.ssafy.trip.domain.plan.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDto {
    private int order;
    private int dateOrder;
    private Long cost;
    private String vehicleName;
    private String spotTitle;
    private String spotImage;
}