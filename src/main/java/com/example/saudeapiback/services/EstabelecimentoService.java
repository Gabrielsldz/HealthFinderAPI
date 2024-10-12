package com.example.saudeapiback.services;

import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import com.example.saudeapiback.dto.EstabelecimentoDTO;
import com.example.saudeapiback.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<EstabelecimentoDTO> getEstabelecimentos(Map<String, Integer> params) {
        Integer codigoTipoUnidade = params.get("codigo_tipo_unidade");
        Integer codigoUf = params.get("codigo_uf");

        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByCodigoTipoUnidadeAndCodigoUf(codigoTipoUnidade, codigoUf);

        return estabelecimentos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EstabelecimentoDTO convertToDto(Estabelecimento estabelecimento) {
        return new EstabelecimentoDTO(
                estabelecimento.getNomeRazaoSocial(),
                estabelecimento.getNomeFantasia(),
                estabelecimento.getCodigoCepEstabelecimento(),
                estabelecimento.getEnderecoEstabelecimento(),
                estabelecimento.getNumeroEstabelecimento(),
                estabelecimento.getBairroEstabelecimento(),
                estabelecimento.getNumeroTelefoneEstabelecimento(),
                estabelecimento.getDescricaoTurnoAtendimento(),
                estabelecimento.getEstabelecimentoFazAtendimentoAmbulatorialSus()
        );
    }
}