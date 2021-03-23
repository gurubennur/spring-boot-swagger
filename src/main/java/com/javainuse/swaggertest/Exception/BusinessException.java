package com.javainuse.swaggertest.Exception;

public class BusinessException extends ServiceException {
	
    private static final long serialVersionUID = 1L;
    
    private final String code;

    public BusinessException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

	public String getCode() {
		return code;
	}
}
