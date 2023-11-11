package com.ssafy.trip.domain.user.service.impl;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import com.ssafy.trip.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserInfo(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new IllegalStateException());

        return user;
    }

    @Override
    public void updateUser(User user) {
        // Long userId를 통해 회원 조회
        // 회원정보에 null이 아닌 값들은 변경?
        String password = passwordEncoder.encode(user.getPassword());
        user.updatePassowrd(password);

        userMapper.update(user);
    }

    @Override
    public void updateStatus(Long userId) {
        userMapper.updateUserStatus(userId, User.Status.WITHDRAWAL);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        userMapper.updatePassword(userId, passwordEncoder.encode(password));
    }
}
