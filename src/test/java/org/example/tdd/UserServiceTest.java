package org.example.tdd;

import org.example.tdd.domain.User;
import org.example.tdd.service.UserService;
import org.example.tdd.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatedPassword;

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl();
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

        // When
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        // Then
        assertNotNull(user, "createUser() should not return null.");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect.");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect.");
        assertEquals(email, user.getEmail(), "User's email is incorrect.");
        assertNotNull(user.getId(), "User id is missing.");
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

}
