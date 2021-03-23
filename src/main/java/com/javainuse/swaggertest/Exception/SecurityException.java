package com.javainuse.swaggertest.Exception;

public class SecurityException extends ServiceException {
	
    private static final long serialVersionUID = 1L;

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException() {
        super();
    }
}
