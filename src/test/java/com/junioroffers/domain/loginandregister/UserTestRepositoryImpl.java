package com.junioroffers.domain.loginandregister;


import com.junioroffers.domain.loginandregister.model.User;
import com.junioroffers.domain.loginandregister.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserTestRepositoryImpl implements UserRepository {
    Map<Long, User> usersMemoryDB = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return usersMemoryDB.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public User save(User user) {
        usersMemoryDB.put(user.getId(), user);
        return user;
    }

}
