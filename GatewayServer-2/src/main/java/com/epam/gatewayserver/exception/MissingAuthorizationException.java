package com.epam.gatewayserver.exception;

public class MissingAuthorizationException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public MissingAuthorizationException(String message) {
        super(message);
    }
}
