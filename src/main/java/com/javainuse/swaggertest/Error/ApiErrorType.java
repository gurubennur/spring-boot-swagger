package com.javainuse.swaggertest.Error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Api Error Type")
public enum ApiErrorType {
	
	@ApiModelProperty("Business Error- check the ApiErrorResponse code value for additional information")
	API_ERROR,
	
	@ApiModelProperty("Authentication/Authorization Error")
	AUTHENTICATION_ERROR,
	
	@ApiModelProperty("Request was invalid")
	INVALID_REQUEST_ERROR,
	
	@ApiModelProperty("Unable to process the request due to internal errors")
	INTERNAL_ERROR
}
