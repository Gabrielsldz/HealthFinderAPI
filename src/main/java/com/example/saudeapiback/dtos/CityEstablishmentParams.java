package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Parâmetros para buscar estabelecimentos por cidade")
public class CityEstablishmentParams {

    @Schema(description = "Código do município para filtrar os estabelecimentos", example = "123456")
    private Integer codigo_municipio;

    @Schema(description = "Código do tipo de unidade para filtrar os estabelecimentos (opcional)", example = "5")
    private Integer codigo_tipo_unidade;

    // Getters e setters
    public Integer getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(Integer codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public Integer getCodigo_tipo_unidade() {
        return codigo_tipo_unidade;
    }

    public void setCodigo_tipo_unidade(Integer codigo_tipo_unidade) {
        this.codigo_tipo_unidade = codigo_tipo_unidade;
    }
}
