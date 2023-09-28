package com.nesum.nesum.user.application.dto;

import java.util.List;

import com.nesum.nesum.shared.application.dto.GenericEntityDTO;

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
public class UserDTO extends GenericEntityDTO {
    
    String name;
    String email;
    String password;
    boolean isActive;
    List<PhoneDTO> phoneList;
    
}
