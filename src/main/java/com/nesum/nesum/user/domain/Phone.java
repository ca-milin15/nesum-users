package com.nesum.nesum.user.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nesum.nesum.shared.domain.GenericEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TBL_USER_PHONE")
public class Phone  extends GenericEntity{
    
    @Column(name = "NUMBER", length = 30)
    String number;
    
    @Column(name = "CITY_CODE", length = 10)
    String cityCode;
    
    @Column(name = "COUNTRY_CODE", length = 10)
    String countryCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    User user;
}
