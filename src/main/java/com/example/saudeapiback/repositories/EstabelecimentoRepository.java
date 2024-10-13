package com.example.saudeapiback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import java.util.List;


@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {

    // Método para buscar o Estabelecimento pelo código CNES
    Estabelecimento findByCodigoCnes(int codigoCnes);

    // Método para buscar estabelecimentos por código de tipo e UF
    List<Estabelecimento> findByCodigoTipoUnidadeAndCodigoUf(Integer codigoTipoUnidade, Integer codigoUf);

    // Método para buscar estabelecimentos por código de tipo e município
    List<Estabelecimento> findByCodigoTipoUnidadeAndCodigoMunicipio(Integer codigoTipoUnidade, Integer codigoMunicipio);

    List<Estabelecimento> findByCodigoCepEstabelecimentoInAndCodigoTipoUnidade(List<String> cepsProximos, int i);

    List<Estabelecimento> findByCodigoCepEstabelecimentoIn(List<String> cepsProximos);
}