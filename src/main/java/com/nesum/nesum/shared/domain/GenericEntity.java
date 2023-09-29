package com.nesum.nesum.shared.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@MappedSuperclass
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GenericEntity {
    
    @Id
    String id;

    @Column(name = "CREATED")
    LocalDateTime createdDate;

    @Column(name = "MODIFIED")
    LocalDateTime modifiedDate;

    @PrePersist
    public void prePersist() {
        this.id = java.util.UUID.randomUUID().toString();
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
}
