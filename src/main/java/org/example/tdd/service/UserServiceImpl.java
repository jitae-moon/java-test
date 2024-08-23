package org.example.tdd.service;

import org.example.tdd.domain.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatedPassword) {

        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("First name is empty.");
        }

        return new User(UUID.randomUUID().toString(), firstName, lastName, email);
    }

}
