package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.entity.User;

public interface UserService {

    User getUserInfo(Long userId);

    void updateUser(User user);

    void updateStatus(Long userId);

    void updatePassword(Long userId, String password);
}
