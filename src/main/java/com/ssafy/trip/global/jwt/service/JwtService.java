package com.ssafy.trip.global.jwt.service;

import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import lombok.NonNull;

public interface JwtService {

    String issueAccessToken(@NonNull UserInfoDto info);

    String issueRefreshToken(Long id);

    TokenDto issueToken(@NonNull UserInfoDto info);

    LoginUserDto parseAccessToken(String accessToken);

    Long parseRefreshToken(String refreshToken);

    void addBlackList(@NonNull String accessToken);

    boolean isBlack(String jwt);
    UserInfoDto parseAccessTokenByBase64(String accessToken);
}
