package com.ssafy.trip.global.jwt.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.ssafy.trip.global.jwt.JwtUtils.*;
import static javax.management.timer.Timer.ONE_MINUTE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenUserInfoDto {
    private Long id;
    private String email;
    private String role;

    public Claims toClaims(int expiresMin) {
        Claims claims = Jwts.claims();

        Date now = new Date();

        claims.put(KEY_ID, this.id);
        claims.put(KEY_EMAIL, this.email);
        claims.put(KEY_ROLE, this.role);
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + expiresMin * ONE_MINUTE));

        return claims;
    }

    public static TokenUserInfoDto from(Claims claims) {
        return TokenUserInfoDto.builder()
                .id(claims.get(KEY_ID, Long.class))
                .email(claims.get(KEY_EMAIL, String.class))
                .role(claims.get(KEY_ROLE, String.class))
                .build();
    }
}
