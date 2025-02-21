package com.internintelligence.movieapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Intern Intelligence Movie API")
                        .version("1.0.0")
                        .description("Advanced Movie Management API with CRUD operations, built using Spring Boot and MongoDB.")
                        .contact(new Contact()
                                .name("Intern Intelligence")
                                .email("support@internintelligence.com")
                                .url("https://internintelligence.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("https://movieapi.internintelligence.com").description("Production Server")
                ));
    }
}
