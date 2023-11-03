package com.ssafy.trip.domain.user.mapper;

import com.ssafy.trip.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insert(User user);

    Optional<User> selectById(Long id);

    void update(User user);
}
