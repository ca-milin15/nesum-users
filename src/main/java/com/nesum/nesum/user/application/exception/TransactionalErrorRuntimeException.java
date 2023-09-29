package com.nesum.nesum.user.application.exception;

public class TransactionalErrorRuntimeException extends RuntimeException{
    
    public TransactionalErrorRuntimeException(String message) {
        super(message);
    }
}
