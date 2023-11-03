package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupDto {
    private String email;
    private String password;
    private User.Status status;
    private User.Role role;
//    private String profileImage;

    public SignupDto(String email, String password) {
        this.email = email;
        this.password = password;
        this.status = User.Status.MEMBER;
        this.role = User.Role.ROLE_USER;
    }

//    public static SignupDto create(String email, String password) {
//        return new SignupDto(email, password);
//    }
}
