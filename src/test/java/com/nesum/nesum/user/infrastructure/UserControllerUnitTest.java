package com.nesum.nesum.user.infrastructure;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nesum.nesum.user.application.dto.PhoneDTO;
import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.application.exception.UserFieldsRuntimeException;
import com.nesum.nesum.user.application.service.UserService;
import com.nesum.nesum.user.infrastructure.controller.UserController;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {
    
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;


    @Test
    @DisplayName("This method should fail because your fields are null")
    void userCreateProcessTestFail() {
        assertThrows(UserFieldsRuntimeException.class, () -> userController.userCreateProcess(createWrongUserDTOObject()));
        verify(userService, times(1)).userCreateProcess(createWrongUserDTOObject());
    }

    @Test
    @DisplayName("This method check the correct work of 'userCreateProcess' method")
    void userCreateProcessTestOk() {
        var userResponseDTO = userController.userCreateProcess(createOkUserDTOObject());
        assertInstanceOf(UserResponseDTO.class, userResponseDTO);
       
        assertAll(() -> {
            assertNotNull(userResponseDTO.getLastLogin());
            assertNotNull(userResponseDTO.getToken());
            assertTrue(!userResponseDTO.getToken().isEmpty());
            assertTrue(userResponseDTO.isActive());
        });
        verify(userService, times(1)).userCreateProcess(createWrongUserDTOObject());
    }

    public static UserDTO createOkUserDTOObject() {
        var phoneList = List.of(new PhoneDTO("312222", "34", "34"));
        return new UserDTO("camilo", "camilo@dominio.cl", "password", false, phoneList);
    }

    public static UserDTO createWrongUserDTOObject() {
        return new UserDTO(null, null, null, false, null);
    }
}
