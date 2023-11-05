package com.ssafy.trip.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtIssuer {
    public String issueToken(@NonNull Claims claims, @NonNull Key secretKey) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
