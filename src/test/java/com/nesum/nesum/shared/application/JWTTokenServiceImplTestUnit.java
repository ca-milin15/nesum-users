package com.nesum.nesum.shared.application;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nesum.nesum.shared.application.service.JWTTokenServiceImpl;
import com.nesum.nesum.user.infrastructure.UserControllerUnitTest;

@ExtendWith(MockitoExtension.class)
public class JWTTokenServiceImplTestUnit {
    
    @InjectMocks
    JWTTokenServiceImpl jwtTokenServiceImpl;

    @Test
    @DisplayName("This method checks the correct work of 'generateToken' method")
    void userCreateProcessTestOk() {
        var token = jwtTokenServiceImpl.generateToken(UserControllerUnitTest.createOkUserDTOObject());
        assertInstanceOf(String.class, token);
    }
}
