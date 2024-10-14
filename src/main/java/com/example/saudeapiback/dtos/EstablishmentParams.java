package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "Parametros de busca por cep", description = "Parâmetros para buscar estabelecimentos por CEP e distância")
public class EstablishmentParams {

    @Schema(description = "CEP de referência", example = "12345678")
    private String cep;

    @Schema(description = "Distância máxima em quilômetros", example = "5")
    private Double distance;

    @Schema(description = "Tipo de estabelecimento", example = "1")
    private String tipo_estabelecimento;
}