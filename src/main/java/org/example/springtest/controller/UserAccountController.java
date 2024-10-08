package org.example.springtest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springtest.dto.UserAccountDto;
import org.example.springtest.dto.request.UserDetailsRequest;
import org.example.springtest.dto.response.UserDetailsResponse;
import org.example.springtest.service.UserAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public UserDetailsResponse createUser(@RequestBody @Valid UserDetailsRequest request) {
        UserAccountDto dto = new ModelMapper().map(request, UserAccountDto.class);

        UserAccountDto savedUser = userAccountService.createUser(dto);

        return new ModelMapper().map(savedUser, UserDetailsResponse.class);
    }

    @GetMapping
    public List<UserDetailsRequest> getUsers() {

        return List.of();
    }

}
