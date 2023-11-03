package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.entity.User;

import java.util.Optional;

public interface UserService {
    void login(String email, String password);

    void signup(String email, String password);

    User getUserInfo(Long userId);

    void updateUser(User user);
}
