package com.example.saudeapiback.dtos;

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