package com.example.saudeapiback.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configurar melhor o swagger
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WRAPPER para a API publica de saude")
                        .description("Um wrapper para facilitar a busca de estabelecimentos de saúde")
                        .version("1.0")
                );
    }

}