package com.javainuse.swaggertest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Hello {
	
	private int helloId;
	private String test;

    @ApiModelProperty(position = 1, required = true, value = "801")
	public int getHelloId() {
		return helloId;
	}

	public void setHelloId(int helloId) {
		this.helloId = helloId;
	}

    @ApiModelProperty(position = 2, required = true, value = "HelloTest")
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}


}
