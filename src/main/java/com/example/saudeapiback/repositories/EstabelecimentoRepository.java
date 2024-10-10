package com.example.saudeapiback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import java.util.List;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {


    List<Estabelecimento> findByCodigoTipoUnidadeAndCodigoUf(Integer codigoTipoUnidade, Integer codigoUf);


}