package com.nesum.nesum.user.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponseDTO userCreateProcess(@RequestBody UserDTO userDTO) {
        return new UserResponseDTO();
    }

}
