package com.example.api.service.exception;

public class CustomerNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Customer is not found";

	public CustomerNotFoundException(String msg) {
		super(msg);
	}

	public CustomerNotFoundException() {
		super(MESSAGE);
	}
}
