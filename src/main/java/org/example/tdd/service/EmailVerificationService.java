package org.example.tdd.service;

import org.example.tdd.domain.User;

public interface EmailVerificationService {

    void scheduleEmailConfirmation(User user);

}
