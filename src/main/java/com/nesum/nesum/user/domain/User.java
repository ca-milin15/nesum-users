package com.nesum.nesum.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import com.nesum.nesum.shared.domain.GenericEntity;
import com.nesum.nesum.user.application.dto.UserResponseDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TBL_USER")
public class User extends GenericEntity {
    
    @Column(name = "NAME", length = 60)
    String name;
    
    @Column(name = "EMAIL", length = 150, unique = true)
    String email;

    @Column(name = "PASSWORD", length = 150)
    String password;
    
    @Column(name = "JWT", length = 500)
    String token;
    
    @Column(name = "LAST_LOGIN")
    LocalDateTime lastLogin;
    
    @Column(name = "IS_ACTIVE")
    boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    List<Phone> phoneList;

    public UserResponseDTO convertToUserResponseDTO() {
        return new UserResponseDTO(
            getId(), 
            ObjectUtils.isEmpty(getCreatedDate()) ? "" : getCreatedDate().toString(), 
            ObjectUtils.isEmpty(getModifiedDate()) ? "" : getModifiedDate().toString(),
            ObjectUtils.isEmpty(getLastLogin()) ? "" : getLastLogin().toString(), 
            getToken(), isActive());
    }
}
