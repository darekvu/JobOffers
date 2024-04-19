package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.UserDto;
import com.junioroffers.domain.loginandregister.dto.UserRegisterRequestDo;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User mapFromRequestDtoToUser(UserRegisterRequestDo userRegisterRequestDo) {
        return User
                .builder()
                .id(userRegisterRequestDo.id())
                .username(userRegisterRequestDo.username())
                .password(userRegisterRequestDo.password())
                .build();
    }
}
