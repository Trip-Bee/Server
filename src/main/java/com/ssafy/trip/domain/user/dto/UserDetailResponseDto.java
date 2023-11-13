package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;

    public static UserDetailResponseDto from(User user) {
        return UserDetailResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .build();
    }
}
