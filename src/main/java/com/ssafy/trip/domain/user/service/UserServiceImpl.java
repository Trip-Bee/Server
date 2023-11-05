package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import com.ssafy.trip.global.error.exception.UserException;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
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
    public TokenUserInfoDto login (String email, String password) {
        User user = userMapper.findByEmail(email).orElseThrow(() -> new UserException());
        String savedPassword = user.getPassword();

        if (!passwordEncoder.matches(password, savedPassword)) {
            throw new UserException();
        }

        return TokenUserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

    @Transactional
    @Override
    public void signup(String email, String password) {
        password = passwordEncoder.encode(password);

        User user = User.create(email, password);


        // TODO 추후 이미지 처리 >> 이미지 요청이 없는 경우 기본 이미지 등록
        userMapper.insert(user);
        System.out.println(email + " " + password);
    }

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
