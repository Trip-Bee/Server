package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.global.jwt.dto.UserInfoDto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    private Long id;
    private String email;
    private String role;
    private String nickname;
    private String profileImage;

    public static LoginUserDto from(UserInfoDto info) {
        return LoginUserDto.builder()
                .id(info.getId())
                .email(info.getEmail())
                .nickname(info.getNickname())
                .profileImage(info.getProfileImage())
                .role(info.getRole())
                .build();
    }
}
