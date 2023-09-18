package com.speaking.clock.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI speakingClockOpenAPI() {
		return new OpenAPI().info(new Info().title("Speaking Clock Application API's")
				.description("API's used in Speaking Clock App").version("1.0"));
	}

}
