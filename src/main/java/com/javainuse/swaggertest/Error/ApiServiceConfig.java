package com.javainuse.swaggertest.Error;

import org.springframework.context.annotation.Bean;

/*
 * Common Beans used by all Api Services
 */
public class ApiServiceConfig {

	@Bean
	public DefaultApiExceptionHandler apiServiceExceptionHandler() {
		return new DefaultApiExceptionHandler();
	}
}
