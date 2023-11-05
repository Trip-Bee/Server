package com.ssafy.trip.global.jwt.service;

import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import lombok.NonNull;

public interface JwtService {
    TokenDto issueToken(@NonNull TokenUserInfoDto info);
    TokenUserInfoDto parseAccessToken(@NonNull String accessToken);
}
