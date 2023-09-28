package com.nesum.nesum.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nesum.nesum.shared.domain.GenericEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "TBL_USER_PHONE")
public class Phone  extends GenericEntity{
    
    @Column(name = "NUMBER")
    String number;
    
    @Column(name = "CITY_CODE")
    String cityCode;
    
    @Column(name = "COUNTRY_CODE")
    String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
