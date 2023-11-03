package com.ssafy.trip.domain.user.entity;

import com.ssafy.trip.global.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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

    public enum Role {
        ROLE_USER,
        ROLE_ADMIN
    }

//    public User(Long id, String email, String password, Status status, String profileImage, Role role, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        super(createdAt, updatedAt);
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.status = status;
//        this.profileImage = profileImage;
//        this.role = role;
//    }

    @Builder
    public User(Long id, String email, String password, Status status, String profileImage, Role role) {
        this.id = id;
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

    public void updatePassowrd(String password) {
        this.password = password;
    }

}
