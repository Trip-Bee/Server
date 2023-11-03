package com.ssafy.trip.domain.user.entity;

import com.ssafy.trip.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private Long id;
    private String userId;
    private String password;
    private String email;
    private Status status;
    private Role role;

}
