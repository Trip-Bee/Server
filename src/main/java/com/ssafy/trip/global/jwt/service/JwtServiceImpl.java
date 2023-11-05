package com.ssafy.trip.global.jwt.service;

import com.ssafy.trip.global.jwt.JwtIssuer;
import com.ssafy.trip.global.jwt.JwtParser;
import com.ssafy.trip.global.jwt.JwtUtils;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import com.ssafy.trip.global.jwt.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.ssafy.trip.global.jwt.JwtUtils.BEARER_PREFIX;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtIssuer jwtIssuer;
    private final JwtParser jwtParser;
    private final JwtUtils jwtUtils;
    private final TokenRepository tokenRepository;

    public TokenDto issueToken(@NonNull TokenUserInfoDto info) {
        String accessToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getAccessTokenExpiredMin()), jwtUtils.getEncodedKey()
        );
        String refreshToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getRefreshTokenExpiredMin()), jwtUtils.getEncodedKey()
        );

        // refresh token 을 redis에 저장
        tokenRepository.save(info.getEmail(), refreshToken, jwtUtils.getRefreshTokenExpiredMin());

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

    // 토큰의 잔여시간 계산
    private long calculateExpiration(@NonNull String token) {
        Claims claims = jwtParser.parseToken(token, jwtUtils.getEncodedKey());
        long expiration = claims.getExpiration().getTime();
        long now = new Date().getTime();

        return expiration - now;
    }

    public void addBlackList(@NonNull String accessToken) {
        // redis에서 refresh token제거
        // redis에서 access token black list처리
        accessToken = accessToken.substring(7);
        TokenUserInfoDto info = parseAccessToken(accessToken);
        long expiration = calculateExpiration(accessToken);
        tokenRepository.save(accessToken, "BLACK_LIST", expiration);
        tokenRepository.delete(info.getEmail());

    }

    public boolean isBlack(String jwt) {
        return tokenRepository.hasKey(jwt);
    }
}
