package com.example.saudeapiback.controllers;


import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import com.example.saudeapiback.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/establishments")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    // Buscar estabelecimentos por código de tipo e UF
    // Criar DTO para response de estabelecimentos posteriormente
    @PostMapping("/get_establishments")
    public ResponseEntity<List<Estabelecimento>> getEstablishments(@RequestBody Map<String, Integer> params) {
        Integer codigoTipoUnidade = params.get("codigo_tipo_unidade");
        Integer codigoUf = params.get("codigo_uf");

        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByCodigoTipoUnidadeAndCodigoUf(codigoTipoUnidade, codigoUf);
        if (estabelecimentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estabelecimentos);
    }





}