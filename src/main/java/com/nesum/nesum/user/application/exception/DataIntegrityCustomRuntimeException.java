package com.nesum.nesum.user.application.exception;

public class DataIntegrityCustomRuntimeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public DataIntegrityCustomRuntimeException(String message) {
        super(message);
    }
}
