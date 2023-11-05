package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.global.jwt.dto.TokenUserInfoDto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginActiveUserDto {
    private Long id;
    private String email;
    private String role;

    public static LoginActiveUserDto from(TokenUserInfoDto info) {
        return LoginActiveUserDto.builder()
                .id(info.getId())
                .email(info.getEmail())
                .role(info.getRole())
                .build();
    }
}
