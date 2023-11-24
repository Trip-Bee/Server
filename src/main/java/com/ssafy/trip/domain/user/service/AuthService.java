package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.dto.SignupRequestDto;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;

public interface AuthService {
    UserInfoDto login(String email, String password);
    void signup(User user);

    TokenDto reissue(String refreshToken);
}
