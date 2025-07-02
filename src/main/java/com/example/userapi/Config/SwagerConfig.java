package com.example.userapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwagerConfig {

	 @Bean
	    public OpenAPI baseOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("User API with JWT")
	                        .version("1.0.0")
	                        .description("REST API with login, registration, JWT auth and Swagger UI"));
	    }
}
