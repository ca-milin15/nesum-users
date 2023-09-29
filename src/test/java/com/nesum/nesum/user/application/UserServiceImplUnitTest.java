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

import javax.validation.ConstraintViolationException;

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
import com.nesum.nesum.user.application.exception.TransactionalErrorRuntimeException;
import com.nesum.nesum.user.application.service.UserServiceImpl;
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
    @DisplayName("This method should fail because userRepository.save() throws DataIntegrityViolationException")
    void userCreateProcessTestFailByDataIntegrityViolationException() {
        var userDTOWrong = UserControllerUnitTest.createWrongUserDTOObject();
        when(jwtTokenServiceImplMocked.generateToken(userDTOWrong)).thenReturn("token");
        when(userRepositoryMocked.save(any())).thenThrow(DataIntegrityViolationException.class);
        when(systemMessageMocked.getError()).thenReturn(TestConfig.initializeSystemMessage());
        assertThrows(DataIntegrityCustomRuntimeException.class,
            () -> userServiceImpl.userCreateProcess(userDTOWrong));
    }

    @Test
    @DisplayName("This method should fail because userRepository.save() throws ConstrationException")
    void userCreateProcessTestFailByGeneralException() {
        var userDTOWrong = UserControllerUnitTest.createWrongUserDTOObject();
        when(jwtTokenServiceImplMocked.generateToken(userDTOWrong)).thenReturn("token");
        when(userRepositoryMocked.save(any())).thenThrow(ConstraintViolationException.class);
        when(systemMessageMocked.getError()).thenReturn(TestConfig.initializeSystemMessage());
        assertThrows(TransactionalErrorRuntimeException.class,
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

    @Test
    @DisplayName("This method check the correct work of 'User.convertToUserResponseDTO' method when attributes are ok")
    void userConvertMethodTestOk() {
        var userDTOOk = UserControllerUnitTest.createOkUserDTOObject();
        var userDomainOk = userDTOOk.convertToEntity("token");
        assertInstanceOf(UserResponseDTO.class, userDomainOk.convertToUserResponseDTO());
    }

    @Test
    @DisplayName("This method check the correct work of 'User.convertToUserResponseDTO' method when attributes are null")
    void userConvertMethodWithAttrEmptyTestOk() {
        var userDTOOk = UserControllerUnitTest.createWrongUserDTOObject();
        var userDomainOk = userDTOOk.convertToEntity("token");
        assertInstanceOf(UserResponseDTO.class, userDomainOk.convertToUserResponseDTO());
    }
    
}
