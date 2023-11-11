package com.ssafy.trip.domain.user.service.impl;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import com.ssafy.trip.domain.user.service.AuthService;
import com.ssafy.trip.global.error.exception.UserException;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.repository.TokenRepository;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserInfoDto login (String email, String password) {
        User user = userMapper.findByEmail(email).orElseThrow(() -> new UserException());
        String savedPassword = user.getPassword();

        if (!passwordEncoder.matches(password, savedPassword)) {
            throw new UserException();
        }

        return UserInfoDto.builder()
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
    public TokenDto reissue(String refreshToken) {
        Long id = jwtService.parseRefreshToken(refreshToken);
        User user = userMapper.findById(id).orElseThrow(() -> new UserException());
        UserInfoDto userInfo = UserInfoDto.from(user);

        // 기존 refresh token 삭제
        tokenRepository.delete(String.valueOf(id));
        TokenDto tokenDto = jwtService.issueToken(userInfo);

        return tokenDto;
    }
}
