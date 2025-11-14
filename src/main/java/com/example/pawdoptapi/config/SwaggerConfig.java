package com.example.pawdoptapi.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI pawdoptApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pawdopt API")
                        .description("Documentación de la API de Pawdopt")
                        .version("1.0"));
    }
}