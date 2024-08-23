package org.example.tdd.service;

import org.example.tdd.domain.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Override
    public void scheduleEmailConfirmation(User user) {
        // Put user details into email queue
        System.out.println("EmailVerificationServiceImpl : scheduleEmailConfirmation()");
    }

}
