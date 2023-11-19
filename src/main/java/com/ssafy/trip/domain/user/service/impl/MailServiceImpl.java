package com.ssafy.trip.domain.user.service.impl;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import com.ssafy.trip.domain.user.service.Email;
import com.ssafy.trip.domain.user.service.HtmlTemplate;
import com.ssafy.trip.domain.user.service.MailService;
import com.ssafy.trip.domain.user.service.MailerSender;
import com.ssafy.trip.global.error.exception.UserException;
import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import com.ssafy.trip.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ssafy.trip.global.error.exception.ExceptionType.INVALID_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final MailerSender mailerSender;
    private final HtmlTemplate htmlTemplate;
    @Override
    public void sendPasswordEmail(final String email) {
        User user = userMapper.findByEmail(email).orElseThrow(() -> new UserException(INVALID_EMAIL));
        UserInfoDto userInfo = UserInfoDto.from(user);
        String accessToken = jwtService.issueAccessToken(userInfo);

        log.debug("access token : {}", accessToken);
        String targetUrl = "프론트엔드 url?token=";
        String link = targetUrl + accessToken;
        log.debug("link : {}", link);

        Map<String, Object> contents = new HashMap<>();
        contents.put("link", link);

        String message = htmlTemplate.build("passwordMail", contents);

        Email passwordEmail = Email.of(email, "비밀번호 변경 메일", message);

        mailerSender.sendMail(passwordEmail);
    }

}
