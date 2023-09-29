package com.nesum.nesum.shared.application.service;

import com.nesum.nesum.user.application.dto.UserDTO;

public interface TokenService {
        
        String generateToken(UserDTO userDTO);
}
