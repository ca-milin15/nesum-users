package com.nesum.nesum.shared.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.nesum.nesum.shared.application.dto.ErrorOutDTO;
import com.nesum.nesum.user.application.exception.DataIntegrityCustomRuntimeException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorOutDTO runtimeExceptionDefault(RuntimeException ex, WebRequest request) {
		return new ErrorOutDTO(ex.getMessage());
	}

	@ExceptionHandler(DataIntegrityCustomRuntimeException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorOutDTO dataIntegrityCustomRuntimeException(DataIntegrityCustomRuntimeException ex, WebRequest request) {
		return new ErrorOutDTO(ex.getMessage());
	}
	
    final String METHOD_EXCEPTION_MESSAGE_FORMAT = "The field: %s, %s ";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ResponseBody
	public ErrorOutDTO methodArgumentNotValidException(MethodArgumentNotValidException ex,
													WebRequest request) {
        FieldError fieldError = ex.getFieldError();
		return new ErrorOutDTO(String.format(METHOD_EXCEPTION_MESSAGE_FORMAT, 
            fieldError.getField(), 
            fieldError.getDefaultMessage())
        );
	}
}
