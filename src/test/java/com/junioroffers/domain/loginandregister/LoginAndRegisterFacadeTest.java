package com.junioroffers.domain.loginandregister;

import com.junioroffers.domain.loginandregister.dto.RegistrationResponseDto;
import com.junioroffers.domain.loginandregister.dto.UserDto;
import com.junioroffers.domain.loginandregister.dto.UserRegisterRequestDto;
import com.junioroffers.domain.loginandregister.exception.UsernameNotFoundException;
import com.junioroffers.domain.loginandregister.repository.UserTestRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class LoginAndRegisterFacadeTest {

    private LoginAndRegisterFacade underTest;

    @BeforeEach
    void setUp() {
        underTest = new LoginAndRegisterFacade(new UserTestRepositoryImpl());
    }

    @Test
    void should_find_user_by_username() {
//         Given
        UserRegisterRequestDto user = new UserRegisterRequestDto("example", "password");
        RegistrationResponseDto registeredUser = underTest.registerUser(user);
//         When
        UserDto userDto = underTest.selectUserByUsername(user.username());
//         Then
        assertThat(userDto.username()).isEqualTo("example");
        assertThat(userDto.password()).isEqualTo("password");

    }

    @Test
    void should_throw_exception_when_user_not_found() {
//        Given
        String username = "example";
        //When
        //Then
        assertThatThrownBy(() -> underTest.selectUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(String.format("User with %s username not found", username));
    }

    @Test
    void should_register_user() {
//         Given
        UserRegisterRequestDto requestDo = new UserRegisterRequestDto("example", "password");
//        // When
        RegistrationResponseDto registrationResponseDto = underTest.registerUser(requestDo);
//        // Then
        assertAll(
                () -> assertThat(registrationResponseDto.created()).isTrue(),
                () -> assertThat(registrationResponseDto.username()).isEqualTo("example")
        );
    }

}