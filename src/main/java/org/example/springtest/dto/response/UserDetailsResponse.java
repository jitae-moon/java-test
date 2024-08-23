package org.example.springtest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailsResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

}
