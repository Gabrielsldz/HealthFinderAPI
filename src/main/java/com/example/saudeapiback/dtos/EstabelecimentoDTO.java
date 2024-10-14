package com.example.saudeapiback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name ="Campos retornados sobre estabelecimentos")
public record EstabelecimentoDTO(
        String nomeRazaoSocial,
        String nomeFantasia,
        String codigoCepEstabelecimento,
        String enderecoEstabelecimento,
        String numeroEstabelecimento,
        String bairroEstabelecimento,
        String numeroTelefoneEstabelecimento,
        String descricaoTurnoAtendimento,
        String estabelecimentoFazAtendimentoAmbulatorialSus
) {}