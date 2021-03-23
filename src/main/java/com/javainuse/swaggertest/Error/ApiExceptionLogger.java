package com.javainuse.swaggertest.Error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

public interface ApiExceptionLogger {
	
	Logger getLogger();

	default void logException(Throwable throwable) {
		Throwable rootCause = ExceptionUtils.getRootCause(throwable);
		
		if (rootCause != null && rootCause != throwable) {
			getLogger().info("Encountered exception: {} rootCause: {} message: {}", 
					throwable.getClass(), rootCause.getClass(), throwable.getMessage(), throwable);
		} else {
			getLogger().info("Encountered exception: {} message: {}", 
					throwable.getClass(), throwable.getMessage(), throwable);
		}
	}
}
