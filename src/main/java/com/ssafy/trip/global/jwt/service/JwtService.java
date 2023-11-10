package com.ssafy.trip.global.jwt.service;

import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import lombok.NonNull;

public interface JwtService {
    TokenDto issueToken(@NonNull TokenUserInfoDto info);
    TokenUserInfoDto parseToken(@NonNull String accessToken);

    void addBlackList(@NonNull String accessToken);

    boolean isBlack(String jwt);
}
