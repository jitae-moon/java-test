package org.example.tdd.service;

import org.example.tdd.domain.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password, String repeatedPassword);

}
