package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.dto.SignupDto;
import com.ssafy.trip.domain.user.dto.SignupRequest;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login (String email, String password) {
    }

    @Override
    public void signup(String email, String password) {
        password = passwordEncoder.encode(password);

//        SignupDto signupDto = new SignupDto(email, password);

        User user = User.create(email, password);

        // TODO 추후 이미지 처리 >> 이미지 요청이 없는 경우 기본 이미지 등록
        userMapper.insert(user);
        System.out.println(email + " " + password);
    }


}
