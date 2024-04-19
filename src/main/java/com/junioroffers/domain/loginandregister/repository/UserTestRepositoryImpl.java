package com.junioroffers.domain.loginandregister;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

}
