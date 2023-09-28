package com.nesum.nesum.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.nesum.nesum.shared.domain.GenericEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Entity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TBL_USER")
public class User extends GenericEntity {
    
    @Column(name = "NAME")
    String name;
    
    @Column(name = "EMAIL")
    String email;
    
    @Column(name = "JWT")
    String token;
    
    @Column(name = "LAST_LOGIN")
    LocalDateTime lastLogin;
    
    @Column(name = "IS_ACTIVE")
    boolean isActive;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Phone> phones;
}
