package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;

public interface UserService {
    TokenUserInfoDto login(String email, String password);

    void logout(String token, Long expiration);
    void signup(String email, String password);

    User getUserInfo(Long userId);

    void updateUser(User user);

    void updateStatus(Long userId);
}
