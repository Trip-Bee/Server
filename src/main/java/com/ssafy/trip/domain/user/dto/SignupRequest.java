package com.ssafy.trip.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String password;

//  프로필 이미지
//    private String profileImage;
}
