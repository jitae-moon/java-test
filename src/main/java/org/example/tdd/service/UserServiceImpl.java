package org.example.tdd.service;

import org.example.tdd.domain.User;
import org.example.tdd.repository.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailVerificationService emailVerificationService;

    public UserServiceImpl(UserRepository userRepository, EmailVerificationService emailVerificationService) {
        this.userRepository = userRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatedPassword) {

        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("First name is empty.");
        }

        User user = new User(UUID.randomUUID().toString(), firstName, lastName, email);

        boolean isUserCreated;

        try {
            isUserCreated = userRepository.save(user);
        } catch (RuntimeException e) {
            throw new UserServiceException(e.getMessage());
        }

        if (!isUserCreated) throw new UserServiceException("Could not create user");

        try {
            emailVerificationService.scheduleEmailConfirmation(user);
        } catch (RuntimeException e) {
            throw new EmailNotificationServiceException(e.getMessage());
        }

        return user;
    }

}
