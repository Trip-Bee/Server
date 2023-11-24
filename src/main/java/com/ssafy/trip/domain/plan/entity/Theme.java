package com.ssafy.trip.domain.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    private Long id;
    private String name;

    public Theme(String name) {
        this.name = name;
    }

    public static Theme create(Long id, String name) {
        return new Theme(id, name);
    }
}
