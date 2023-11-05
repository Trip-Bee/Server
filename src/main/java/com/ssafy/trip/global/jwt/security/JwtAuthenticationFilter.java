package com.ssafy.trip.global.jwt.security;

import com.ssafy.trip.domain.user.dto.LoginActiveUserDto;
import com.ssafy.trip.global.error.exception.TokenException;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private  static final String REFRESH_HEADER = "Refresh";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtToken(request);
        checkBlackList(jwt);
        authenticate(request, jwt);

        filterChain.doFilter(request, response);
    }

    private static String getJwtToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token != null) {
            token = token.replaceAll("\"", "");
        }
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }

        return null;
    }

    private void checkBlackList(String jwt) {
        if (jwt != null && jwtService.isBlack(jwt)) {
            throw new TokenException();
        }
    }

    private void authenticate(HttpServletRequest request, String jwt) {
        TokenUserInfoDto tokenUserInfoDto = null;

        System.out.println("jwt : " + jwt);

        if (StringUtils.hasText(jwt)) {
            // jwt 파싱
            // claim에서 꺼낸 정보를 통해 AuthenticationToken을 생성 후 SecurityContext에 저장
            try {
                System.out.println("========= parse pre =======");
                tokenUserInfoDto = jwtService.parseAccessToken(jwt);
                System.out.println("========= parse post =======");
                LoginActiveUserDto loginActiveUserDto = LoginActiveUserDto.from(tokenUserInfoDto);
                System.out.println("========= " + loginActiveUserDto + " =======");
                saveLoginUserInSecurityContext(loginActiveUserDto);

            } catch (ExpiredJwtException ex) {
                System.out.println("ExpiredJwtException");
                String refreshToken = request.getHeader(REFRESH_HEADER);
                // refresh Token 파싱
                // refresh Token 만료되었는지 확인
                // access token만 만료된 경우 예외
                // access, refresh token 모두 만료된 경우 예외
            } catch (RuntimeException ex) {
                SecurityContextHolder.clearContext();
                System.out.println("============== runtime ex =============");
                ex.printStackTrace();
                throw new TokenException();
            }
        }
    }

    private static void saveLoginUserInSecurityContext(LoginActiveUserDto loginActiveUserDto) {
        System.out.println("===== saveLoginUserInSecurityContext ======");
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                loginActiveUserDto, "",
                Arrays.asList(new SimpleGrantedAuthority(loginActiveUserDto.getRole()))
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("===== saveLoginUserInSecurityContext 완료 ======");
    }
}
