package com.ssafy.trip.domain.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String name;

    public static Vehicle create(Long id, String name) {
        return new Vehicle(id, name);
    }
}
