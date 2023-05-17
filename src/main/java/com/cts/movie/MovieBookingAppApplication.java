package com.cts.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.movie.filter.JWTFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class MovieBookingAppApplication {
	
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
	FilterRegistrationBean fb= new FilterRegistrationBean();
	fb.setFilter(new JWTFilter());
	fb.addUrlPatterns("/api/v1/*");
	return fb;
	
		
	}
	
	@Configuration
	class openApiConfig{
		@Bean
		public OpenAPI customconfig()
		{
			final String securitySchemeName="bearerAuth";
			return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
					.components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
							.name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
							.bearerFormat("JWT")));
							
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingAppApplication.class, args);
	}

}
