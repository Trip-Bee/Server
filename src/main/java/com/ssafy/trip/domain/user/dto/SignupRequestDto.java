package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String profileImage;

    public User toEntity() {
        return User.create(email, password, nickname, profileImage);
    }
}
