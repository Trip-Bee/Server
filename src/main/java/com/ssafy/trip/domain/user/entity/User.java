package com.ssafy.trip.domain.user.entity;

import com.ssafy.trip.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private Long id;
    private String email;
    private String password;
    private Status status;
    private String profileImage;
    private Role role;


    public enum Status {
        MEMBER,
        WITHDRAWAL
    }

    @Builder
    private User(String email, String password, Status status, String profileImage, Role role) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.profileImage = profileImage;
        this.role = role;
    }

    public static User create(String email, String password) {
        return User.builder().email(email)
                .password(password)
                .status(Status.MEMBER)
                .role(Role.ROLE_USER)
                .build();
    }

}
