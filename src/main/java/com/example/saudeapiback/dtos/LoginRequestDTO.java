package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name ="Modelo da requisicao de login")
public record LoginRequestDTO (String email, String password){
}
