package com.ssafy.trip.domain.plan.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDto implements Comparable<DetailsDto> {
    private int order;
    private Long cost;
    private String vehicleName;
    private String spotTitle;
    private String spotImage;

    @Override
    public int compareTo(DetailsDto o) {
        return Integer.compare(this.order, o.order);
    }
}