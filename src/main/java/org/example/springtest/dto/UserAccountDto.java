package org.example.springtest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAccountDto {

    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;

}
