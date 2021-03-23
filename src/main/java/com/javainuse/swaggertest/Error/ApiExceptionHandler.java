package com.javainuse.swaggertest.Error;

import com.javainuse.swaggertest.Exception.BusinessException;

public interface ApiExceptionHandler extends ApiExceptionLogger, ApiErrorResponseBuilder {

	default ApiErrorResponse handleApiException(BusinessException exception, ApiErrorType type) {
		logException(exception);
		return buildApiErrorResponse(exception, type);
	}

	default ApiErrorResponse handleApiException(Exception exception, ApiErrorType type) {
		logException(exception);
		return buildApiErrorResponse(exception, type);
	}

}
