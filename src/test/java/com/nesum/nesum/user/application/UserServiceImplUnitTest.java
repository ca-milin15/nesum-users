package com.nesum.nesum.user.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.application.exception.UserFieldsRuntimeException;
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


    @Test
    @DisplayName("This method should fail because your fields are null")
    void userCreateProcessTestFail() {
        assertThrows(UserFieldsRuntimeException.class,
            () -> userServiceImpl.userCreateProcess(UserControllerUnitTest.createWrongUserDTOObject()));
    }

    @Test
    @DisplayName("This method check the correct work of 'UserServiceImpl.userCreateProcess' method")
    void userCreateProcessTestOk() {
        when(userRepositoryMocked.save(createUserDomainObject())).thenReturn(createUserDomainObject());
        var userResponseDTO = userServiceImpl.userCreateProcess(UserControllerUnitTest.createOkUserDTOObject());
        
        assertInstanceOf(UserResponseDTO.class, userResponseDTO);
       
        assertAll(() -> {
            assertNotNull(userResponseDTO.getLastLogin());
            assertNotNull(userResponseDTO.getToken());
            assertTrue(!userResponseDTO.getToken().isEmpty());
            assertTrue(userResponseDTO.isActive());
        });
        verify(userRepositoryMocked, times(1)).save(createUserDomainObject());
    }

    public static User createUserDomainObject() {
        return new User("Camilo", "camilo@gmail.com", "token", "token", LocalDateTime.now(), false, null);
    }
    
}
