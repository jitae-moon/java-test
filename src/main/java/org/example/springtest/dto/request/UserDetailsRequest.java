package org.example.springtest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailsRequest {

    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Email
    private String email;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 8, max = 16)
    private String repeatedPassword;

}
