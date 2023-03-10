package com.payment.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket swaggerConfiguration1()
	{
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.any())
					.apis(RequestHandlerSelectors.basePackage("com.payment"))
					.build()
					.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Payment Microservice API Documentation",
				"API for Payment Microservice",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("ASHRAF NOMANI", "http://Youtube.com", "anomani786@hotmail.com"),
				"API Licence",
				"http://Youtube.com",
				Collections.emptyList());
	}
}
