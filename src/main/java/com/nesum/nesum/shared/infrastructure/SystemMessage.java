package com.nesum.nesum.shared.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "system-messages")
public class SystemMessage {
    
    Error error;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Error {
        public String dataIntegrityError;
        public String emailConstraintError;
    }
}
