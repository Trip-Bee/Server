package com.ssafy.trip.global.jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.domain.user.dto.LoginUserDto;
import com.ssafy.trip.global.dto.Response;
import com.ssafy.trip.global.error.exception.ExceptionType;
import com.ssafy.trip.global.error.exception.TokenException;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static com.ssafy.trip.global.error.exception.ExceptionType.INVALID_TOKEN;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtToken(request);
        log.info("gkgkgkgkgk {}", jwt);

        // 로그아웃 체크
        if (isLogout(jwt, response)) {
            return;
        }
        // 토큰 파싱 체크
        if (jwt != null && !authenticate(response, jwt)) {
            return;
        }

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

    private boolean isLogout(String jwt, HttpServletResponse response) throws IOException {
        try {
            if (jwt != null && jwtService.isBlack(jwt)) {
                throw new TokenException(INVALID_TOKEN);
            }
        } catch (TokenException ex) {
            SecurityContextHolder.clearContext();
            log.info(ex.getMessage());
            sendError(response, ex);
            return true;
        }
        return false;
    }

    private boolean authenticate(HttpServletResponse response, String jwt) throws IOException {
        UserInfoDto userInfoDto = null;
        log.debug("jwt : {}", jwt);

        if (StringUtils.hasText(jwt)) {
            // jwt 파싱
            // claim에서 꺼낸 정보를 통해 AuthenticationToken을 생성 후 SecurityContext에 저장
            try {
                LoginUserDto loginUserDto = jwtService.parseAccessToken(jwt);
                saveLoginUserInSecurityContext(loginUserDto);
            } catch (TokenException ex) {
                SecurityContextHolder.clearContext();
                log.info(ex.getMessage());

                sendError(response, ex);

                return false;
            }
        }

        return true;
    }

    private void sendError(HttpServletResponse response, TokenException ex) throws IOException {
        response.setStatus(ex.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        ExceptionType exceptionType = ex.getExceptionType();
        writer.write(objectMapper.writeValueAsString(
                Response.fail(exceptionType.getHttpStatus().name(), exceptionType.getErrorMessage())
        ));

        writer.flush();
    }

    private static void saveLoginUserInSecurityContext(LoginUserDto loginUserDto) {
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                loginUserDto, "",
                Arrays.asList(new SimpleGrantedAuthority(loginUserDto.getRole()))
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
