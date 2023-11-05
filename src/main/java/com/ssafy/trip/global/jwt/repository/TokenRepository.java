package com.ssafy.trip.global.jwt.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class TokenRepository {
    private final RedisTemplate<String, String> redisTemplate;
//    private static final String KEY_PREFIX = "refreshToken::";

//    public void save(String userEmail, String token, int expiresMin) {
//        String key = KEY_PREFIX + userEmail;
//        redisTemplate.opsForValue().set(key, token, Duration.ofMinutes(expiresMin));
//        redisTemplate.expire(key, expiresMin, TimeUnit.MINUTES); // 만료 시간 설정
//    }

//    email, refresh token, expiresMin
//    access token, BLACK_LIST, expiresMin
    public void save(String key, String value, long expiresMin) {
//        String key = KEY_PREFIX + userEmail;
        if (expiresMin > 0) {
            redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(expiresMin));
            redisTemplate.expire(key, expiresMin, TimeUnit.MINUTES); // 만료 시간 설정
        }
    }

    public void delete(final String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(final String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
