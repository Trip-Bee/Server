package com.ssafy.trip.global.jwt.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String KEY_PREFIX = "refreshToken::";

    public void save(String userEmail, String token, int expiresMin) {
        String key = KEY_PREFIX + userEmail;
        redisTemplate.opsForValue().set(key, token, Duration.ofMinutes(expiresMin));
        redisTemplate.expire(key, expiresMin, TimeUnit.MINUTES); // 만료 시간 설정
    }
}
