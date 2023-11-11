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

    public static LoginUserDto from(UserInfoDto info) {
        return LoginUserDto.builder()
                .id(info.getId())
                .email(info.getEmail())
                .role(info.getRole())
                .build();
    }
}
