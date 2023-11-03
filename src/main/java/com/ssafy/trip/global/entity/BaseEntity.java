package com.ssafy.trip.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
