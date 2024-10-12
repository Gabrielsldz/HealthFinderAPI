package com.example.saudeapiback.dto;

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