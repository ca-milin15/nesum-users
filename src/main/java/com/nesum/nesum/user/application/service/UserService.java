package com.nesum.nesum.user.application.service;

import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;

public interface UserService {
    
    UserResponseDTO userCreateProcess(UserDTO userDTO);
}
