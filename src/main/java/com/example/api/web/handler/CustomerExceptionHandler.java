package com.example.api.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.service.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandler {
    
	@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseStatusException CustomerNotFound(CustomerNotFoundException e) {
		return  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
