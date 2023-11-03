package com.ssafy.trip.domain.user.service;

public interface UserService {
    void login(String email, String password);

    void signup(String email, String password);
}
