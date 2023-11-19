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

import static com.ssafy.trip.global.error.exception.ExceptionType.*;


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
        User user = userMapper.findByEmail(email).orElseThrow(() -> new UserException(INVALID_EMAIL));
        String savedPassword = user.getPassword();

        if (!passwordEncoder.matches(password, savedPassword)) {
            throw new UserException(INVALID_PASSWORD);
        }

        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

    @Transactional
    @Override
    public void signup(User user) {
        // TODO 탈퇴한 회원의 경우 기존 정보를 입력받은 정보로 수정, 없던 회원이면 추가, 기존에 있는 MEMBER인 회원이면 예외
        String password = passwordEncoder.encode(user.getPassword());

        user.updatePassowrd(password);

        // TODO 추후 이미지 처리 >> 이미지 요청이 없는 경우 기본 이미지 등록
        // TODO 추후 재가입의 경우 상태가 WITHDRAWL인거 찾고 해당하면 입력한 user 정보로 update, 아니면 insert

        if (userMapper.countByEmail(user.getEmail()) > 0) {
            throw new UserException(DUPLICATED_USER);
        }

        userMapper.insert(user);
    }

    @Override
    public TokenDto reissue(String refreshToken) {
        Long id = jwtService.parseRefreshToken(refreshToken);
        User user = userMapper.findById(id).orElseThrow(() -> new UserException(INVALID_USER));
        UserInfoDto userInfo = UserInfoDto.from(user);

        // 기존 refresh token 삭제
        tokenRepository.delete(String.valueOf(id));
        TokenDto tokenDto = jwtService.issueToken(userInfo);

        return tokenDto;
    }
}
