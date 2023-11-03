package com.ssafy.trip.domain.user.mapper;

import com.ssafy.trip.domain.user.dto.SignupDto;
import com.ssafy.trip.domain.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insert(User user);

    Optional<User> selectById(Long id);
}
