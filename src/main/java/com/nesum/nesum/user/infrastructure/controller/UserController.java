package com.nesum.nesum.user.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.application.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    UserService userService;

    @PostMapping
	@ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponseDTO userCreateProcess(@Validated @RequestBody UserDTO userDTO) {
        return userService.userCreateProcess(userDTO);
    }

}
