package com.javainuse.swaggertest.Error;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.swaggertest.Exception.BusinessException;
import com.javainuse.swaggertest.Exception.InvalidRequestException;
import com.javainuse.swaggertest.Exception.ResourceNotFoundException;
import com.javainuse.swaggertest.Exception.ServiceUnavailableException;

/*
 * Setting Order to LOWEST_PRECEDENCE so service-specific ControllerAdvice can override 
 * any of these ExceptionHandlers by setting Order to HIGHEST_PRECEDENCE.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultApiExceptionHandler implements ApiExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;

	@Override
	public Logger getLogger() {
		return log;
	}

	// This is a workaround to unwrap CompletionExceptions and handle their
	// cause found on SO:
	// https://stackoverflow.com/questions/41681309/how-to-handle-wrapped-exceptions-in-a-spring-exception-handler
	// Spring may fix this in a future release to automatically unwrap
	// CompletionExceptions
	@ExceptionHandler
	public ModelAndView handle(CompletionException exception, HttpServletRequest request,
			HttpServletResponse response) {
		return handlerExceptionResolver.resolveException(request, response, null, (Exception) exception.getCause());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(ResourceNotFoundException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(NoSuchElementException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handle(HttpMessageNotReadableException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handle(InvalidRequestException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handle(MethodArgumentNotValidException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handle(ConstraintViolationException exception) {
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public ApiErrorResponse handle(SecurityException exception) {
		return handleApiException(exception, ApiErrorType.AUTHENTICATION_ERROR);
	}

	@ExceptionHandler
	public ApiErrorResponse handle(MethodArgumentTypeMismatchException exception, HttpServletResponse response) {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	public ApiErrorResponse handle(ConversionFailedException exception, HttpServletResponse response) {
		Exception rootCause = (Exception) ExceptionUtils.getRootCause(exception);

		if (rootCause instanceof ResourceNotFoundException) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return handleApiException(rootCause, ApiErrorType.INVALID_REQUEST_ERROR);
		}

		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return handleApiException(exception, ApiErrorType.INVALID_REQUEST_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiErrorResponse handle(BusinessException exception) {
		return handleApiException(exception, ApiErrorType.API_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public ApiErrorResponse handle(ServiceUnavailableException exception) {
		return handleApiException(exception, ApiErrorType.INTERNAL_ERROR);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handle(Exception exception) {
		return handleApiException(exception, ApiErrorType.INTERNAL_ERROR);
	}
}
