package com.junioroffers.domain.loginandregister.repository;

import com.junioroffers.domain.loginandregister.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    User save(User user);
}
