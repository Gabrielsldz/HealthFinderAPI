package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name ="Modelo da requisicao de registro")
public record RegisterRequestDTO (String name, String email, String password){
}
