package com.nesum.nesum.shared.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GenericEntityDTO implements Serializable {
    
    String id;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
    
}
