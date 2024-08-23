package org.example.tdd.repository;

import org.example.tdd.domain.User;

public interface UserRepository {

    boolean save(User user);

}
