package org.example.tdd;

import org.example.tdd.domain.User;
import org.example.tdd.repository.UserRepository;
import org.example.tdd.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import javax.management.RuntimeErrorException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatedPassword;

    @BeforeEach
    void beforeEach() {
        firstName = "John";
        lastName = "Cena";
        email = "test@test.com";
        password = "qwe123";
        repeatedPassword = "qwe123";
    }

    @DisplayName("Create user object")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        // Given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        // When
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        // Then
        assertNotNull(user, "createUser() should not return null.");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect.");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect.");
        assertEquals(email, user.getEmail(), "User's email is incorrect.");
        assertNotNull(user.getId(), "User id is missing.");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class)); // Verify save() invokes only once
    }

    @DisplayName("Empty first name causes exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        // Given
        firstName = "";
        String expectedMessage = "First name is empty.";

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatedPassword);
        }, "First name is empty.");

        assertEquals(expectedMessage, exception.getMessage(), "Exception message is not correct.");
    }

    @DisplayName("Mock object throws exception")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        // Given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);

        // When & Then
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatedPassword);
        }, "Should have thrown UserServiceException.");
    }

    @DisplayName("EmailNotificationException handled")
    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        // Given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);
        Mockito.doThrow(EmailNotificationServiceException.class).when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));

        // Do nothing
//        Mockito.doNothing().when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));

        // When & Then
        assertThrows(EmailNotificationServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatedPassword);
        }, "EmailNotificationServiceException should be invoked.");
        Mockito.verify(emailVerificationService, Mockito.times(1)).scheduleEmailConfirmation(Mockito.any(User.class));
    }

    @DisplayName("Real Schedule email confirmation executed")
    @Test
    void testCreateUser_whenUserCreated_schedulesEmailConfirmation() {
        // Given
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);
        Mockito.doCallRealMethod().when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));

        // When & Then
        userService.createUser(firstName, lastName, email, password, repeatedPassword);
        Mockito.verify(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));
    }

}
