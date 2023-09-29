package com.nesum.nesum.user.application.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nesum.nesum.shared.application.service.TokenService;
import com.nesum.nesum.shared.infrastructure.SystemMessage;
import com.nesum.nesum.user.application.dto.UserDTO;
import com.nesum.nesum.user.application.dto.UserResponseDTO;
import com.nesum.nesum.user.application.exception.DataIntegrityCustomRuntimeException;
import com.nesum.nesum.user.domain.User;
import com.nesum.nesum.user.infrastructure.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    TokenService tokenService;
    SystemMessage systemMessage;

    @Override
    public UserResponseDTO userCreateProcess(UserDTO userDTO) {
        var token = tokenService.generateToken(userDTO);
        var user = userPersist(userDTO.convertToEntity(token));
        return user.convertToUserResponseDTO();
    }

    private User userPersist(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new DataIntegrityCustomRuntimeException(systemMessage.getError().getEmailConstraintError());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(systemMessage.getError().getGeneralTransactionalError());
        }
    }
    
}
