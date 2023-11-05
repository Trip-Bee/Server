package com.ssafy.trip.global.jwt;

import com.ssafy.trip.global.error.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtParser {
    public Claims parseToken(String token, Key sercretKey) {
        Claims claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(sercretKey)
                    .build()
                    .parseClaimsJws(token).getBody();

            System.out.println("===========claims===========");
            System.out.println(claims);

        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            System.out.print("============ 예외: ==============");
            ex.printStackTrace();
            throw new TokenException();
        }

        return claims;
    }
}
