package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegistrationResponseDto;
import com.junioroffers.domain.loginandregister.dto.UserDto;
import com.junioroffers.domain.loginandregister.dto.UserRegisterRequestDto;
import com.junioroffers.domain.loginandregister.exception.UsernameNotFoundException;
import com.junioroffers.domain.loginandregister.model.User;
import com.junioroffers.domain.loginandregister.repository.UserRepository;
import com.junioroffers.domain.loginandregister.util.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginAndRegisterFacade {
    private final UserRepository userRepository;

    public UserDto selectUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s username not found", username)));

        return UserMapper.mapToUserDto(foundUser);
    }

    public RegistrationResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        User user = UserMapper.mapFromRequestDtoToUser(userRegisterRequestDto);
        userRepository.save(user);
        return new RegistrationResponseDto(user.getId(), user.getUsername(), true);
    }

}
