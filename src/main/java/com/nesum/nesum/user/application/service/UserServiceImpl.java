package com.nesum.nesum.user.application.service;

import org.springframework.stereotype.Service;

import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.infrastructure.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Override
    public UserResponseDTO userCreateProcess(UserDTO userDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'userCreateProcess'");
    }
    
}
