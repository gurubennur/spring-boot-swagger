package com.javainuse.swaggertest.Exception;

public class InvalidRequestException extends ServiceException {

	private static final long serialVersionUID = 1L;

	// This exception shouldn't have a code field, it's not a BusinessException
	@Deprecated
	private String code;

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException() {
		super();
	}

	public String getCode() {
		return code;
	}

}
