package com.example.todo_list_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("TodoList Backend API")
            .description("TodoList Backend Endpoints in Spring boot")
            .version("v0.0.1")
            .description("TodoList Backend Endpoints in Spring boot"));
  }
}
