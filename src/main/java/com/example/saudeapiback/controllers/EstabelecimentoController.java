package com.example.saudeapiback.controllers;

import com.example.saudeapiback.dto.EstabelecimentoDTO;
import com.example.saudeapiback.services.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/establishment")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping("/get_establishments_by_city")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishments(@RequestBody Map<String, Integer> params) {
        List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstabelecimentos(params);
        if (estabelecimentoDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estabelecimentoDTOs);
    }

    // Novo método para buscar por CEP
    @GetMapping("/get_establishments_by_cep")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishmentsByCep(@RequestBody Map<String, Object> params) {
        try {
            List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstablishmentsByCep(params);
            if (estabelecimentoDTOs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(estabelecimentoDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}