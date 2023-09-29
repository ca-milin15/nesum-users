package com.nesum.nesum.user.application.dto;

import static com.nesum.nesum.shared.infrastructure.StaticConstants.EMAIL_FORMAT_REGEX;
import static com.nesum.nesum.shared.infrastructure.StaticConstants.ONLY_TEXT_REGEX;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nesum.nesum.shared.application.dto.GenericEntityDTO;
import com.nesum.nesum.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends GenericEntityDTO {
    
    public UserDTO(String id, String createdDate, String modifiedDate,
        String name, String email, String password, boolean isActive, List<PhoneDTO> phoneList) {
        super(id, createdDate, modifiedDate);
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.phones = phoneList;
    }

	@NotEmpty
    @Size(min = 10, max = 60)
	@Pattern(regexp = ONLY_TEXT_REGEX )
    String name;

	@NotEmpty
    @Size(min = 10, max = 150)
	@Pattern(regexp = EMAIL_FORMAT_REGEX )
    String email;

	@NotEmpty
    @Size(min = 10, max = 150)
    @NotEmpty
    String password;

    boolean isActive;

	@Valid
    List<PhoneDTO> phones;
    
    public User convertToEntity(String token){
        var user = User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .token(token)
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .build();
        user.setPhoneList(this.phones != null && !this.phones.isEmpty() 
            ? this.phones.stream().map(phoneDTO -> phoneDTO.convertToEntity(user)).collect(Collectors.toList())
            : new ArrayList<>());
        return user;
    } 
}
