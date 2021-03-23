package com.javainuse.swaggertest.Exception;

public class ResourceNotFoundException extends ServiceException {
	
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super();
    }
}
