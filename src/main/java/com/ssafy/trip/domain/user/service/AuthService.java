package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;

public interface AuthService {
    UserInfoDto login(String email, String password);
    void signup(String email, String password);

    TokenDto reissue(String refreshToken);
}
