package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private UserInfoDto userInfo;
    private TokenDto token;
}
