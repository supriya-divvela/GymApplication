package com.epam.gatewayserver.eception;

public class UnAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String message) {
		super(message);
	}
}
