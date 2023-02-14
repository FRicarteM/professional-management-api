package com.fabtec.professionalmanagement.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
		
	@Bean
	public OpenAPI custumerOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Professional Management Api")
						.version("V.1")
						.description("An API destined for the study of development and integration of API's. "
								+ "This API is the first step of three of the a big project of integration"
								+ " and communication between API's.")
						.termsOfService("http://professional-management-api.fabtec.bom/api/link-for-test")
						.license(new License()
								.name("Apache 2.0")
								.url("http://professional-management-api.fabtec.bom/api/link-for-test")
								)
					);
	}
	
}
