package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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

    @Transactional
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
}
