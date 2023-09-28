package com.nesum.nesum.user.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO implements Serializable{
    
    LocalDateTime lastLogin;
    String token;
    boolean isActive;
}
