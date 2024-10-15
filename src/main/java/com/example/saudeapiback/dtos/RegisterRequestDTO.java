package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name ="DTO de requisição de registro")
public record RegisterRequestDTO (String name, String email, String password){
}
