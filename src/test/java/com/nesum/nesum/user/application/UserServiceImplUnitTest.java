package com.nesum.nesum.user.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.nesum.nesum.shared.application.service.JWTTokenServiceImpl;
import com.nesum.nesum.shared.infrastructure.SystemMessage;
import com.nesum.nesum.shared.infrastructure.TestConfig;
import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.application.exception.DataIntegrityCustomRuntimeException;
import com.nesum.nesum.user.application.service.UserServiceImpl;
import com.nesum.nesum.user.domain.User;
import com.nesum.nesum.user.infrastructure.UserControllerUnitTest;
import com.nesum.nesum.user.infrastructure.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {
    
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepositoryMocked;

    @Mock
    JWTTokenServiceImpl jwtTokenServiceImplMocked;

    @Mock
    SystemMessage systemMessageMocked;


    @Test
    @DisplayName("This method should fail because your fields are null")
    void userCreateProcessTestFail() {
        var userDTOWrong = UserControllerUnitTest.createWrongUserDTOObject();
        when(jwtTokenServiceImplMocked.generateToken(userDTOWrong)).thenReturn("token");
        when(userRepositoryMocked.save(any())).thenThrow(DataIntegrityViolationException.class);
        when(systemMessageMocked.getError()).thenReturn(TestConfig.initializeSystemMessage());
        assertThrows(DataIntegrityCustomRuntimeException.class,
            () -> userServiceImpl.userCreateProcess(userDTOWrong));
    }

    @Test
    @DisplayName("This method check the correct work of 'UserServiceImpl.userCreateProcess' method")
    void userCreateProcessTestOk() {
        var userDTOOk = UserControllerUnitTest.createOkUserDTOObject();
        var userDomainOk = userDTOOk.convertToEntity("token");
        when(jwtTokenServiceImplMocked.generateToken(userDTOOk)).thenReturn("token");
        when(userRepositoryMocked.save(any())).thenReturn(userDomainOk);
        var userResponseDTO = userServiceImpl.userCreateProcess(userDTOOk);
        
        assertInstanceOf(UserResponseDTO.class, userResponseDTO);
       
        assertAll(() -> {
            assertNotNull(userResponseDTO.getLastLogin());
            assertNotNull(userResponseDTO.getToken());
            assertTrue(!userResponseDTO.getToken().isEmpty());
            assertTrue(userResponseDTO.isActive());
        });
        verify(userRepositoryMocked, times(1)).save(any());
    }

    public static User createUserDomainObject() {
        return new User("Camilo", "camilo@gmail.com", "token", "token", LocalDateTime.now(), false, null);
    }
    
}
