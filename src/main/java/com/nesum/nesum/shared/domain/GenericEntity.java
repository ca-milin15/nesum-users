package com.nesum.nesum.shared.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GenericEntity {
    
    @Id
    String id;

    @Column(name = "CREATED")
    LocalDateTime createdDate;

    @Column(name = "MODIFIED")
    LocalDateTime modifiedDate;

}
