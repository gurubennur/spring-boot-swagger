package com.javainuse.swaggertest.Error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Base Response for all Api Services to that error responses are consistent
 */
@ApiModel(description = "Api Error Response")
public class ApiErrorResponse implements ErrorResponse {

	@ApiModelProperty(value = "Type of Api Error", required = true)
	private ApiErrorType type;
	
	@ApiModelProperty("Error code, will only be available for type API_ERROR")
	private String code;
	
	@ApiModelProperty("Error message")
	private String message;
	
	@ApiModelProperty("Exception stack trace- QA please do not include this field in tests as it's behavior is subject to change")
	private String trace;

	public ApiErrorType getType() {
		return type;
	}

	public void setType(ApiErrorType type) {
		this.type = type;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}
}
