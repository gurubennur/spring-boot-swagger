package com.javainuse.swaggertest.Exception;

public class ServiceUnavailableException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceUnavailableException(String message) {
		super(message);
	}

	public ServiceUnavailableException() {
		super();
	}
}
