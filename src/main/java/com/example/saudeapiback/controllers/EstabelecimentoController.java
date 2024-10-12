package com.example.saudeapiback.controllers;


import com.example.saudeapiback.domain.estabelecimento.Estabelecimento;
import com.example.saudeapiback.dto.EstabelecimentoDTO;
import com.example.saudeapiback.repositories.EstabelecimentoRepository;
import com.example.saudeapiback.services.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/establishment")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @PostMapping("/get_establishments")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishments(@RequestBody Map<String, Integer> params) {
        List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstabelecimentos(params);
        if (estabelecimentoDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estabelecimentoDTOs);
    }
}






