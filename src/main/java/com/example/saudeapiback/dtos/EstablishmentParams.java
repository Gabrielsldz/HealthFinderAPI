package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Parâmetros para buscar estabelecimentos por CEP e distância")
public class EstablishmentParams {

    @Schema(description = "CEP de referência", example = "12345678")
    private String cep;

    @Schema(description = "Distância máxima em quilômetros", example = "5")
    private Double distance;

    @Schema(description = "Tipo de estabelecimento", example = "1")
    private String tipo_estabelecimento;

    // Getters e setters
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getTipo_estabelecimento() {
        return tipo_estabelecimento;
    }

    public void setTipo_estabelecimento(String tipo_estabelecimento) {
        this.tipo_estabelecimento = tipo_estabelecimento;
    }
}