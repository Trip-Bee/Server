package com.ssafy.trip.global.jwt.service.impl;

import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.error.exception.TokenException;
import com.ssafy.trip.global.jwt.JwtUtils;
import com.ssafy.trip.global.jwt.dto.TokenDto;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.repository.TokenRepository;
import com.ssafy.trip.global.jwt.service.JwtService;
import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static com.ssafy.trip.global.error.exception.ExceptionType.EXPIRED_TOKEN;
import static com.ssafy.trip.global.error.exception.ExceptionType.INVALID_TOKEN;
import static javax.management.timer.Timer.ONE_MINUTE;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtUtils jwtUtils;
    private final TokenRepository tokenRepository;

    private static final String CLAIM_EMAIL = "email";

    private static final String CLAIM_NICKNAME = "nickname";

    private static final String CLAIM_PROFILE_IMAGE = "profileImage";
    private static final String CLAIM_ROLE = "role";

    public String issueAccessToken(@NonNull UserInfoDto info) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(info.getId()));

        claims.put(CLAIM_EMAIL, info.getEmail());
        claims.put(CLAIM_ROLE, info.getRole());
        claims.put(CLAIM_NICKNAME, info.getNickname());
        claims.put(CLAIM_PROFILE_IMAGE, info.getProfileImage());
        return issueToken(claims, jwtUtils.getAccessTokenExpiredMin(), jwtUtils.getEncodedKey());
    }

    public String issueRefreshToken(@NonNull Long id) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(id));
        return issueToken(claims, jwtUtils.getRefreshTokenExpiredMin(), jwtUtils.getEncodedKey());
    }

    @Override
    public TokenDto issueToken(@NonNull UserInfoDto info) {
        String accessToken = issueAccessToken(info);
        String refreshToken = issueRefreshToken(info.getId());
        tokenRepository.save(refreshToken, accessToken, jwtUtils.getRefreshTokenExpiredMin());
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String issueToken(Claims claims, int expiresMin, Key secretKey) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiresMin * ONE_MINUTE))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public LoginUserDto parseAccessToken(String accessToken) {
        Claims payload = parseToken(accessToken, jwtUtils.getEncodedKey());

        return LoginUserDto.builder()
                .id(Long.valueOf(payload.getId()))
                .email(payload.get(CLAIM_EMAIL, String.class))
                .role(payload.get(CLAIM_ROLE, String.class))
                .nickname(payload.get(CLAIM_NICKNAME, String.class))
                .profileImage(payload.get(CLAIM_PROFILE_IMAGE, String.class))
                .build();
    }

    public Long parseRefreshToken(String refreshToken) {
        Claims claims = parseToken(refreshToken, jwtUtils.getEncodedKey());
        return Long.valueOf(claims.getId());
    }

    private Claims parseToken(String token, Key secretKey) {
        Claims payload = null;
        try {
            payload = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token).getBody();

            log.debug("===========claims===========");
            log.debug("claims : {}", payload);
            log.debug("claims ID : {}", payload.getId());
        }
        catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.debug("============ 예외: ==============");
            log.warn("파싱 예외");
            throw new TokenException(INVALID_TOKEN);
        } catch (ExpiredJwtException ex) {
            log.warn("만료된 토큰");
            throw new TokenException(EXPIRED_TOKEN);
        }

        return payload;
    }


    // 토큰의 잔여시간 계산
    private long calculateExpiration(@NonNull String accessToken) {
        Claims claims = parseToken(accessToken, jwtUtils.getEncodedKey());

        long expiration = claims.getExpiration().getTime();
        long now = new Date().getTime();

        return expiration - now;
    }

    public void addBlackList(@NonNull String accessToken) {
        // redis에서 refresh token제거
        // redis에서 access token black list처리
        accessToken = accessToken.substring(7);
        LoginUserDto loginUserDto = parseAccessToken(accessToken);
        long expiration = calculateExpiration(accessToken);
        tokenRepository.save(accessToken, "BLACK_LIST", expiration);
        tokenRepository.delete(String.valueOf(loginUserDto.getId()));

    }

    public boolean isBlack(String jwt) {
        return tokenRepository.hasKey(jwt);
    }

//    JWT의 payload에서 직접 데이터 꺼내오기 >> UserInfoDto
    public UserInfoDto parseAccessTokenByBase64(String accessToken) {
//        JWT는 head.payload.sign 형태로 이루어져 있다. 
//        여기서 payload를 꺼내기 위한 분리
        String payload = accessToken.split("\\.")[1];

        String decodePayload = new String(Base64.getDecoder().decode(payload));

        BasicJsonParser jsonParser = new BasicJsonParser();

        Map<String, Object> map = jsonParser.parseMap(decodePayload);

        return UserInfoDto.builder()
                .id(Long.valueOf((String)(map.get("jti"))))
                .email((String)map.get(CLAIM_EMAIL))
                .role((String)map.get(CLAIM_ROLE))
                .build();
    }
}
