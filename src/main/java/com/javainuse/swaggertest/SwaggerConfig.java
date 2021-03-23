package com.javainuse.swaggertest;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	protected TypeResolver typeResolver;

	@Bean
	public Docket postsApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.groupName("public-api").apiInfo(apiInfo()).genericModelSubstitutes(Iterable.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(CompletableFuture.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)),
						newRule(typeResolver.resolve(CompletableFuture.class, WildcardType.class),
								typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false).select().paths(postPaths()).build()
				.tags(new Tag("HelloService", "API Operation on Hello"),
						new Tag("Credit Cards", "APIs for operations on credit cards"));

		docket.globalResponseMessage(RequestMethod.GET,
				ImmutableList.of(
						new ResponseMessageBuilder().code(400).message("Bad Request")
								.responseModel(new ModelRef("	response = ApiErrorResponse.class, ")).build(),
						new ResponseMessageBuilder().code(500).message("Internal Server Error")
								.responseModel(new ModelRef("ApiErrorResponse")).build()))
				.globalResponseMessage(RequestMethod.POST,
						Arrays.asList(
								new ResponseMessageBuilder().code(400).message("Bad Request")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(500).message("Internal Server Error")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(503).message("Service Unavailable")
										.responseModel(new ModelRef("ApiErrorResponse")).build()))
				.globalResponseMessage(RequestMethod.PUT,
						Arrays.asList(
								new ResponseMessageBuilder().code(400).message("Bad Request")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(404).message("Not Found")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(500).message("Internal Server Error")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(503).message("Service Unavailable")
										.responseModel(new ModelRef("ApiErrorResponse")).build()))
				.globalResponseMessage(RequestMethod.DELETE,
						Arrays.asList(
								new ResponseMessageBuilder().code(404).message("Not Found")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(500).message("Internal Server Error")
										.responseModel(new ModelRef("ApiErrorResponse")).build(),
								new ResponseMessageBuilder().code(503).message("Service Unavailable")
										.responseModel(new ModelRef("ApiErrorResponse")).build()));

		return docket;
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Torry Harries Business solution API")
				.description("THBS API reference for developers").termsOfServiceUrl("http://THBS.com")
				.license("THBS License").licenseUrl("THBS@gmail.com").version("1.0").build();
	}

}