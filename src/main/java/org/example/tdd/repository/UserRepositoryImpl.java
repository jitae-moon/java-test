package org.example.tdd.repository;

import org.example.tdd.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public boolean save(User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return true;
        }

        return false;
    }

}
