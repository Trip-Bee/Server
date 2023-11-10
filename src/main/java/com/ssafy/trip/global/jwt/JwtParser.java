package com.ssafy.trip.global.jwt;

import com.ssafy.trip.global.error.exception.TokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

import static com.ssafy.trip.global.error.exception.ExceptionType.EXPIRED_TOKEN;
import static com.ssafy.trip.global.error.exception.ExceptionType.INVALID_TOKEN;

@Slf4j
@Component
public class JwtParser {
    public Claims parseToken(String token, Key sercretKey) {
        Claims claims = null;
//        claims = Jwts.parserBuilder()
//                .setSigningKey(sercretKey)
//                .build()
//                .parseClaimsJws(token).getBody();
//
//        log.debug("===========claims===========");
//        log.debug("claims : {}", claims);

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(sercretKey)
                    .build()
                    .parseClaimsJws(token).getBody();

            log.debug("===========claims===========");
            log.debug("claims : {}", claims);

        }
        catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.debug("============ 예외: ==============");
            log.warn("파싱 예외");
            throw new TokenException(INVALID_TOKEN);
        } catch (ExpiredJwtException ex) {
            log.warn("만료된 토큰");
            throw new TokenException(EXPIRED_TOKEN);
        }

        // ExpiredJwtException 예외는 파싱요청하는 부분에서 처리
        return claims;
    }
}
