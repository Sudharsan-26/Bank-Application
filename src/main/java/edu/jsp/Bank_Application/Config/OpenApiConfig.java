package edu.jsp.Bank_Application.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI customApi()
	{
		return new OpenAPI().info(new Info()
				                .title("Banking application")
				                .version("1.1V")
				                .description("Rest api end point for banking application")
				                .contact(
				                		new Contact()
				                		.email("bank@gmail.com")
				                		.name("Development team")
				                		.url("www.bank.com")
				                		)
				                
				                .license(
				                		new License()
				                		.name("Apache 2.0")
				                		.url("www.apache.2.0.com")
				                		)
				                );
	}

}
