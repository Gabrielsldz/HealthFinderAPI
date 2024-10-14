package com.example.saudeapiback.controllers;

import com.example.saudeapiback.dtos.EstabelecimentoDTO;
import com.example.saudeapiback.dtos.EstablishmentParams;
import com.example.saudeapiback.service.EstabelecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishments(
     @RequestBody Map<String, Integer> params) {
        List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstabelecimentos(params);
        if (estabelecimentoDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estabelecimentoDTOs);
    }
    @Operation(
            summary = "Buscar estabelecimentos por CEP e distância",
            description = "Este endpoint retorna uma lista de estabelecimentos próximos ao CEP de referência informado, com base na distância máxima e tipo de estabelecimento fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, estabelecimentos retornados"),
            @ApiResponse(responseCode = "404", description = "Nenhum estabelecimento encontrado dentro da distância informada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição, verifique os parâmetros enviados")
    })
    @PostMapping("/get_establishments_by_cep")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishmentsByCep(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Parâmetros para a busca de estabelecimentos",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = EstablishmentParams.class)
                    )
            )@RequestBody Map<String, Object> params) {
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