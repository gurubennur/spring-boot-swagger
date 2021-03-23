package com.javainuse.swaggertest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.swaggertest.Error.ApiErrorResponse;
import com.javainuse.swaggertest.Exception.BusinessException;
import com.javainuse.swaggertest.Exception.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "HelloService" })
public class HelloController {

	private static final String ID = "id";

	@ApiOperation(value = "Create a Hello")
	@ApiResponses(value = { @ApiResponse(code = 201, response = Hello.class, message = "Hello Response"),
			@ApiResponse(code = 404, response = ApiErrorResponse.class, message = "Not Found"),
			@ApiResponse(code = 422, response = ApiErrorResponse.class, message = "Error processing request, check the error code in the response for more detail") })
	@PostMapping(path = "/api/javainuse/", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Hello createHello(@RequestBody @Valid Hello hello) {
		Hello helloResponse = hello;
		return helloResponse;
	}

	@ApiOperation(value = "getGreeting", nickname = "getGreeting")
	@ApiResponses(value = { @ApiResponse(code = 500, response = ApiErrorResponse.class, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Hello.class, responseContainer = "List") })
	@RequestMapping(method = RequestMethod.GET, value = "/api/javainuse/{id}")
	public List<Hello> sayHello(
			@ApiParam(value = "testId", required = true) @PathVariable(ID) final int institutuionId) {
		if (institutuionId == 11) {

			throw new BusinessException("wrong instution id", HttpStatus.BAD_REQUEST.toString());
		}

		if (institutuionId == 12) {
			throw new ResourceNotFoundException("institution id: {} not found");
		}
		ArrayList<Hello> arrayList = new ArrayList<>();
		arrayList.add(new Hello());
		return arrayList;
	}

}
