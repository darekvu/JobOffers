package com.junioroffers.domain.loginandregister;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    User save(User user);
}
