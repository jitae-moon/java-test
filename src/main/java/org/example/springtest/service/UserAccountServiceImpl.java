package org.example.springtest.service;

import lombok.RequiredArgsConstructor;
import org.example.springtest.domain.UserAccount;
import org.example.springtest.dto.UserAccountDto;
import org.example.springtest.exception.UserServiceException;
import org.example.springtest.repository.UserAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserAccountDto createUser(UserAccountDto dto) {
        if (userAccountRepository.findByEmail(dto.getEmail()) != null) {
            throw new UserServiceException("Entity already exists.");
        }

        ModelMapper mapper = new ModelMapper();
        UserAccount entity = mapper.map(dto, UserAccount.class);

        String userId = UUID.randomUUID().toString();
        entity.setUserId(userId);
        entity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        UserAccount savedUser = userAccountRepository.save(entity);

        return mapper.map(savedUser, UserAccountDto.class);
    }

    @Override
    public List<UserAccountDto> getUsers(int page, int limit) {
        return null;
    }

    @Override
    public UserAccountDto getUser(String userId) {
        return null;
    }

}
