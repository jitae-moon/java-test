package org.example.springtest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String encryptedPassword;

}
