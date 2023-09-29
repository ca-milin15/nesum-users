package com.nesum.nesum.user.infrastructure;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
import com.nesum.nesum.user.application.service.UserServiceImpl;
import com.nesum.nesum.user.infrastructure.controller.UserController;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {
    
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userServiceImplMocked;


    @Test
    @Disabled("This test is disabled because the null fields is checked with @Validated is done in the integration test")
    @DisplayName("This method should fail because your fields are null")
    void userCreateProcessTestFail() {
        assertThrows(UserFieldsRuntimeException.class, () -> userController.userCreateProcess(createWrongUserDTOObject()));
        verify(userServiceImplMocked, times(1)).userCreateProcess(createWrongUserDTOObject());
    }

    @Test
    @DisplayName("This method check the correct work of 'userCreateProcess' method")
    void userCreateProcessTestOk() {
        var userDTO = createOkUserDTOObject();
        when(userServiceImplMocked.userCreateProcess(userDTO)).thenReturn(createOkUserResponseDTOObject());
        var userResponseDTO = userController.userCreateProcess(userDTO);
        assertInstanceOf(UserResponseDTO.class, userResponseDTO);
       
        assertAll(() -> {
            assertNotNull(userResponseDTO.getLastLogin());
            assertNotNull(userResponseDTO.getToken());
            assertTrue(!userResponseDTO.getToken().isEmpty());
            assertTrue(userResponseDTO.isActive());
        });
        verify(userServiceImplMocked, times(1)).userCreateProcess(userDTO);
    }

    public static UserResponseDTO createOkUserResponseDTOObject() {
        return new UserResponseDTO("UUID", LocalDateTime.now().toString(), LocalDateTime.now().toString(),
            LocalDateTime.now().toString(), "JWTTOKEN", true);
    }

    public static UserDTO createOkUserDTOObject() {
        var phoneList = List.of(new PhoneDTO("3122222", "34", "34"));
        return new UserDTO("UUID", LocalDateTime.now().toString(), LocalDateTime.now().toString(),
            "camilo Rivera", "camiloriveraa@dominio.cl", "passwordpassword", false, phoneList);
    }

    public static UserDTO createWrongUserDTOObject() {
        return new UserDTO(null, null, null, null, null, null, false, null);
    }
}
