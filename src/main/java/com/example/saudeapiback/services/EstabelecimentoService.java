package com.example.saudeapiback.services;

import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import com.example.saudeapiback.dto.EstabelecimentoDTO;
import com.example.saudeapiback.repositories.EstabelecimentoRepository;
import com.example.saudeapiback.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private CepService cepService;

    // Método para buscar estabelecimentos por cidade
    public List<EstabelecimentoDTO> getEstabelecimentos(Map<String, Integer> params) {
        Integer codigoMunicipio = params.get("codigo_municipio");
        Integer codigoTipoUnidade = params.get("codigo_tipo_unidade");

        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByCodigoTipoUnidadeAndCodigoMunicipio(codigoTipoUnidade, codigoMunicipio);

        return estabelecimentos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EstabelecimentoDTO> getEstablishmentsByCep(Map<String, Object> params) throws IOException, InterruptedException {
        String referenceCep = (String) params.get("cep");
        Double maxDistanceKm = Double.valueOf((Integer) params.get("distance"));
        String tipoEstabelecimento = (String) params.get("tipo_estabelecimento");
        String filePath = "src/main/resources/ceps_bd.txt"; // Caminho para o arquivo ceps_bd.txt

        List<String> cepsProximos = cepService.findCepsWithinDistance(referenceCep, maxDistanceKm, filePath);

        if (cepsProximos.isEmpty()) {
            throw new IllegalArgumentException("No CEPs found within the specified distance");
        }

        List<Estabelecimento> estabelecimentos;
        if (tipoEstabelecimento != null) {
            estabelecimentos = estabelecimentoRepository.findByCodigoCepEstabelecimentoInAndCodigoTipoUnidade(cepsProximos, Integer.parseInt(tipoEstabelecimento));
        } else {
            estabelecimentos = estabelecimentoRepository.findByCodigoCepEstabelecimentoIn(cepsProximos);
        }

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
