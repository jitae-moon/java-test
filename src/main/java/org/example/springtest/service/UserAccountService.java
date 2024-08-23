package org.example.springtest.service;

import org.example.springtest.dto.UserAccountDto;

import java.util.List;

public interface UserAccountService {

    UserAccountDto createUser(UserAccountDto dto);
    List<UserAccountDto> getUsers(int page, int limit);
    UserAccountDto getUser(String userId);

}
