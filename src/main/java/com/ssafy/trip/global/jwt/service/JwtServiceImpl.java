package com.ssafy.trip.global.jwt.service;

import com.ssafy.trip.global.jwt.JwtIssuer;
import com.ssafy.trip.global.jwt.JwtParser;
import com.ssafy.trip.global.jwt.JwtUtils;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import com.ssafy.trip.global.jwt.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.trip.global.jwt.JwtUtils.BEARER_PREFIX;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtIssuer jwtIssuer;
    private final JwtParser jwtParser;
    private final JwtUtils jwtUtils;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto issueToken(@NonNull TokenUserInfoDto info) {
        String accessToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getAccessTokenExpiredMin()), jwtUtils.getEncodedKey()
        );
        String refreshToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getRefreshTokenExpiredMin()), jwtUtils.getEncodedKey()
        );

        // refresh token 을 redis에 저장
        refreshTokenRepository.save(info.getEmail(), refreshToken, jwtUtils.getRefreshTokenExpiredMin());

        return TokenDto.builder()
                .grantType(BEARER_PREFIX)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtUtils.getAccessTokenExpiredMin())
                .build();
    }

    public TokenUserInfoDto parseAccessToken(@NonNull String accessToken) {
        Claims claims = jwtParser.parseToken(accessToken, jwtUtils.getEncodedKey());
        if(claims == null) {
            return null;
        }

        return TokenUserInfoDto.from(claims);
    }
}
