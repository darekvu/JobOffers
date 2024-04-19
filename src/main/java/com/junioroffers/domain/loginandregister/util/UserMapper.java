package com.junioroffers.domain.loginandregister.util;

import com.junioroffers.domain.loginandregister.dto.UserDto;
import com.junioroffers.domain.loginandregister.dto.UserRegisterRequestDto;
import com.junioroffers.domain.loginandregister.model.User;

import java.util.Random;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User mapFromRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDo) {
        return User
                .builder()
                .id(new Random().nextLong())
                .username(userRegisterRequestDo.username())
                .password(userRegisterRequestDo.password())
                .build();
    }
}
