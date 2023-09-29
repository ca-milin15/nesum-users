package com.nesum.nesum.user.application.dto;

import com.nesum.nesum.shared.application.dto.GenericEntityDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO extends GenericEntityDTO{
    
    String lastLogin;
    String token;
    boolean isActive;

    public UserResponseDTO(String id, String created, String modified, String lastLogin, String token, boolean isActive) {
        super(id, created, modified);
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

}
