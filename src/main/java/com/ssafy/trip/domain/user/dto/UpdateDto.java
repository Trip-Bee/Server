package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String profileImage;

    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImage(profileImage)
                .build();
    }

}
